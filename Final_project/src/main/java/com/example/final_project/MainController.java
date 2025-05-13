package com.example.final_project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;

public class MainController {
    @FXML private TextField itemField;
    @FXML private TextField quantityField;
    @FXML private TextField categoryField;
    @FXML private ComboBox<String> categoryBox;
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> nameColumn;
    @FXML private TableColumn<Item, Integer> quantityColumn;
    @FXML private TableColumn<Item, Boolean> boughtColumn;
    @FXML private TableColumn<Item, String> categoryColumn;


    private int userId;//
    private ObservableList<Item> items = FXCollections.observableArrayList();

    public void setUserId(int id) {//метод, чтобы подгрузить все данные именно для текущего пользователя
        this.userId = id;
        loadCategories();
        loadItems();
    }


    @FXML
    private void initialize() {//настраиваем, какие данные будут отображаться в колонках таблицы
        nameColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        quantityColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());
        boughtColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleBooleanProperty(cell.getValue().isBought()).asObject());
        categoryColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCategoryName()));

        tableView.setItems(items);

        tableView.setRowFactory(tv -> {//добавляем возможность двойным щелчком по строке поменять флаг
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Item item = row.getItem();
                    markAsBought(item);
                }
            });
            return row;
        });
    }

    @FXML
    private void handleAddItem() {
        String name = itemField.getText();//получаем значения из полей
        String category = categoryBox.getValue();
        String quantityText = quantityField.getText();
        int quantity;


        if (name.isEmpty() || category == null || quantityText.isEmpty()) {
            showAlert("Заполните все поля.");
            return;
        }

        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            showAlert("Введите корректное число в поле количества.");
            return;
        }

        try (Connection conn = DBAdapter.getConnection()) {
            //проверяем, существует ли категория в базе
            PreparedStatement catStmt = conn.prepareStatement("SELECT id FROM categories WHERE name = ? AND user_id = ?");
            catStmt.setString(1, category);
            catStmt.setInt(2, userId);
            ResultSet rs = catStmt.executeQuery();
            int catId;
            if (rs.next()) {
                catId = rs.getInt("id");
            } else {
                //если категория не найдена, добавляем её
                PreparedStatement insertCat = conn.prepareStatement("INSERT INTO categories(name, user_id) VALUES (?, ?) RETURNING id");
                insertCat.setString(1, category);
                insertCat.setInt(2, userId);
                ResultSet newCat = insertCat.executeQuery();
                newCat.next();
                catId = newCat.getInt("id");
            }
            //добавляем товар в базу данных
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO items(name, quantity, bought, category_id) VALUES (?, ?, false, ?)");
            stmt.setString(1, name);
            stmt.setInt(2, quantity);
            stmt.setInt(3, catId);
            stmt.executeUpdate();

            //обновляем таблицу
            itemField.clear();
            quantityField.clear();
            loadItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void markAsBought(Item item) {
        try (Connection conn = DBAdapter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE items SET bought = NOT bought WHERE id = ?");
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
            loadItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод для добавления категории вручную
    @FXML
    private void handleAddCategory() {
        String categoryName = categoryField.getText();

        if (categoryName.isEmpty()) {
            showAlert("Введите название категории.");
            return;
        }

        try (Connection conn = DBAdapter.getConnection()) {
            //проверяем, есть ли уже такая категория для данного пользователя
            PreparedStatement checkCategory = conn.prepareStatement("SELECT id FROM categories WHERE name = ? AND user_id = ?");
            checkCategory.setString(1, categoryName);
            checkCategory.setInt(2, userId);
            ResultSet rs = checkCategory.executeQuery();

            if (rs.next()) {
                showAlert("Категория с таким именем уже существует.");
                return;
            }

            //вставляем новую категорию
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO categories(name, user_id) VALUES (?, ?)");
            stmt.setString(1, categoryName);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

            categoryField.clear();
            loadCategories();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ошибка при добавлении категории.");
        }
    }

    private void loadCategories() {//получает из БД список всех категорий пользователя и заполняет выпадающий список
        categoryBox.getItems().clear();
        try (Connection conn = DBAdapter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM categories WHERE user_id = ?");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categoryBox.getItems().add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItems() {//получает из бд все товары, связанные с текущим пользователем, и отображает их в таблице
        items.clear();
        try (Connection conn = DBAdapter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT i.id, i.name, i.quantity, i.bought, i.category_id, c.name AS category_name " +
                            "FROM items i JOIN categories c ON i.category_id = c.id WHERE c.user_id = ?"
            );
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getBoolean("bought"),
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteItem() {//удаляет выбранный товар из базы
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert("Выберите товар для удаления.");
            return;
        }

        try (Connection conn = DBAdapter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM items WHERE id = ?");
            stmt.setInt(1, selectedItem.getId());
            stmt.executeUpdate();
            loadItems();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ошибка при удалении товара.");
        }
    }


    @FXML
    private void handleLogout() {//возвращает пользователя на экран входа
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root, 800, 600);
            Stage stage = (Stage) itemField.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
