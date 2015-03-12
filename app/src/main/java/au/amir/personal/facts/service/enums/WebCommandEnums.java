package au.amir.personal.facts.service.enums;

public enum WebCommandEnums {
    GET_DATA("Fetching Data/JSON");

    private String dialogMsg;

    private WebCommandEnums(String dialogMsg) {
        this.dialogMsg = dialogMsg;
    }

    public String getDialogMsg() {
        return dialogMsg;
    }
}


