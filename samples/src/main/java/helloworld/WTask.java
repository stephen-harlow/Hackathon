package helloworld;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benawad on 3/27/16.
 */
public class WTask {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_by_id")
    @Expose
    private int createdById;
    @SerializedName("created_by_request_id")
    @Expose
    private String createdByRequestId;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("completed")
    @Expose
    private boolean completed;
    @SerializedName("starred")
    @Expose
    private boolean starred;
    @SerializedName("list_id")
    @Expose
    private int listId;
    @SerializedName("revision")
    @Expose
    private int revision;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The createdById
     */
    public int getCreatedById() {
        return createdById;
    }

    /**
     *
     * @param createdById
     * The created_by_id
     */
    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    /**
     *
     * @return
     * The createdByRequestId
     */
    public String getCreatedByRequestId() {
        return createdByRequestId;
    }

    /**
     *
     * @param createdByRequestId
     * The created_by_request_id
     */
    public void setCreatedByRequestId(String createdByRequestId) {
        this.createdByRequestId = createdByRequestId;
    }

    /**
     *
     * @return
     * The dueDate
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     *
     * @param dueDate
     * The due_date
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     *
     * @return
     * The completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     *
     * @param completed
     * The completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     *
     * @return
     * The starred
     */
    public boolean isStarred() {
        return starred;
    }

    /**
     *
     * @param starred
     * The starred
     */
    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    /**
     *
     * @return
     * The listId
     */
    public int getListId() {
        return listId;
    }

    /**
     *
     * @param listId
     * The list_id
     */
    public void setListId(int listId) {
        this.listId = listId;
    }

    /**
     *
     * @return
     * The revision
     */
    public int getRevision() {
        return revision;
    }

    /**
     *
     * @param revision
     * The revision
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }
}
