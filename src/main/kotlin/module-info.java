module MvcGame{
    requires javafx.graphics;
    requires  javafx.controls;
    requires  javafx.fxml;
    requires kotlin.stdlib;

    exports cz.cvut.fit.miadp;
    opens  cz.cvut.fit.miadp to javafx.graphics;
}