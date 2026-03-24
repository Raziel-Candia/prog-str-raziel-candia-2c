module com.example.crud_examendeprueba {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.crud_examendeprueba to javafx.fxml;
    exports com.example.crud_examendeprueba;
}