module OOP {
	requires javafx.controls;
	
	opens application.main to javafx.graphics, javafx.fxml;
}
