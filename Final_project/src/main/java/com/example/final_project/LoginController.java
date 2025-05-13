package com.example.final_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText(); //считываем введённые данные из текстовых полей
        String password = passwordField.getText();

        try (Connection conn = DBAdapter.getConnection()) { //проверяем в базе данных:
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { //если есть — получаем его id, чтобы знать, кто вошёл
                int userId = rs.getInt("id");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml")); //загружаем главное окно
                Parent root = loader.load();
                MainController controller = loader.getController();
                controller.setUserId(userId); //передаём туда userId, чтобы подгружать данные конкретного пользователя
                Stage stage = (Stage) usernameField.getScene().getWindow();//меняем текущую сцену на главное окно
                Scene scene = new Scene(root, 1300, 600);
                stage.setScene(scene);
                stage.centerOnScreen();
            } else {
                showAlert("Неверный логин или пароль.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ошибка подключения к базе данных.");
        }
    }

    @FXML
    private void goToRegister() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Scene scene = new Scene(root, 800, 600);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}