package net.thewesthill.wps.model.DriveFreq.Items.request;

public class DriveFreqItemsRequest {

    private boolean withPermission;

    private boolean withLink;

    private boolean pageSize;

    private boolean pageToken;

    public DriveFreqItemsRequest() {

    }

    public DriveFreqItemsRequest(boolean withPermission, boolean withLink, boolean pageSize, boolean pageToken) {
        this.withPermission = withPermission;
        this.withLink = withLink;
        this.pageSize = pageSize;
        this.pageToken = pageToken;
    }

    public boolean isWithPermission() {
        return withPermission;
    }

    public void setWithPermission(boolean withPermission) {
        this.withPermission = withPermission;
    }

    public boolean isWithLink() {
        return withLink;
    }

    public void setWithLink(boolean withLink) {
        this.withLink = withLink;
    }

    public boolean isPageSize() {
        return pageSize;
    }

    public void setPageSize(boolean pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPageToken() {
        return pageToken;
    }

    public void setPageToken(boolean pageToken) {
        this.pageToken = pageToken;
    }

    @Override
    public String toString() {
        return "DriveFreqItemsRequest{" +
                "withPermission=" + withPermission +
                ", withLink=" + withLink +
                ", pageSize=" + pageSize +
                ", pageToken=" + pageToken +
                '}';
    }
}
