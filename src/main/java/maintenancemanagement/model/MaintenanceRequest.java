package maintenancemanagement.model;

import java.time.LocalDate;

public class MaintenanceRequest {
    private int requestId;
    private String itemName;
    private String problemDescription;
    private LocalDate requestDate;
    private String handledBy;

    public MaintenanceRequest() {}

    public MaintenanceRequest(int requestId, String itemName, String problemDescription, LocalDate requestDate, String handledBy) {
        this.requestId = requestId;
        this.itemName = itemName;
        this.problemDescription = problemDescription;
        this.requestDate = requestDate;
        this.handledBy = handledBy;
    }

    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getProblemDescription() { return problemDescription; }
    public void setProblemDescription(String problemDescription) { this.problemDescription = problemDescription; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }

    public String getHandledBy() { return handledBy; }
    public void setHandledBy(String handledBy) { this.handledBy = handledBy; }

    public String toFileString() {
        return requestId + "|" + itemName + "|" + problemDescription + "|" + requestDate + "|" + handledBy;
    }

    public static MaintenanceRequest fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 5) return null;
        return new MaintenanceRequest(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                LocalDate.parse(parts[3]),
                parts[4]
        );
    }
}
