package MainWindow;

public enum typeOfWindowAdd {
    ADD((byte) 0),
    ADD_IF_MAX((byte)2),
    ADD_IF_MIN((byte)1);

    byte type;
    typeOfWindowAdd(byte b) {
        type = b;
    }

    public byte getType() {
        return type;
    }
}
