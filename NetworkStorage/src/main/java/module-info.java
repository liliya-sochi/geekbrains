module NetworkStorage {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.all;
    requires java.sql;

    /** НЕ ПОНИМАЮ, ЗАЧЕМ ЭТО НУЖНО! */
    exports client;
    exports controller;

    /** И В ЦЕЛОМ, ЗАЧЕМ НУЖЕН ЭТОТ ФАЙЛ! */
}