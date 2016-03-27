public class AllLists {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("owner_type")
    @Expose
    private String ownerType;
    @SerializedName("owner_id")
    @Expose
    private int ownerId;
    @SerializedName("list_type")
    @Expose
    private String listType;
    @SerializedName("public")
    @Expose
    private boolean _public;
    @SerializedName("revision")
    @Expose
    private int revision;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
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
     * The ownerType
     */
    public String getOwnerType() {
        return ownerType;
    }

    /**
     *
     * @param ownerType
     * The owner_type
     */
    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    /**
     *
     * @return
     * The ownerId
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     *
     * @param ownerId
     * The owner_id
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     *
     * @return
     * The listType
     */
    public String getListType() {
        return listType;
    }

    /**
     *
     * @param listType
     * The list_type
     */
    public void setListType(String listType) {
        this.listType = listType;
    }

    /**
     *
     * @return
     * The _public
     */
    public boolean isPublic() {
        return _public;
    }

    /**
     *
     * @param _public
     * The public
     */
    public void setPublic(boolean _public) {
        this._public = _public;
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
