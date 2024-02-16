package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.example.bo.BoFactory;
import org.example.bo.custom.StudentBo;
import org.example.config.FactoryConfiguration;
import org.example.dto.StudentDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class StudentController {
    public TextField txtId;
    public TextField txtAddress;
    public TextField txtname;

    StudentBo studentBo = (StudentBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.STUDENT);

    public void initialize(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        transaction.commit();
        session.close();

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        int id =Integer.parseInt(txtId.getText());
        String name = txtname.getText();
        String address = txtAddress.getText();

        StudentDto studentDto = new StudentDto(id, name, address);
        try {
            boolean isSaved = studentBo.save(studentDto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (Exception e ){
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }finally {
            clearFeilds();
        }
    }
    private void clearFeilds(){
        txtId.clear();
        txtname.clear();
        txtAddress.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        String name = txtname.getText();
        String address = txtAddress.getText();

        StudentDto studentDto = new StudentDto(id, name, address);
        try {
            boolean isUpdated = studentBo.update(studentDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (Exception e ){
                new Alert(Alert.AlertType.WARNING, "somthing wrong").show();
            } finally {
                clearFeilds();

        }

    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("Confirmation");
       alert.setHeaderText("Are you sure?");
       alert.setContentText("Do you want to delete this Student?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.getButtonTypes().setAll(yes, no);
        alert.showAndWait().ifPresent(response -> {
            if (response == yes) {
                boolean isDeleted = false;
                try {
                    isDeleted = studentBo.delete(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();
                }
            }
        });

    }
}
