package rrpssFINAL;

public class Staff {
	/**
	 The name of the staff.
	*/
	private String name;
	/**
	 The gender of the staff.
	*/
	private String gender;
	/**
	 The id of the staff.
	*/
	private int staffId;
	/**
	 The job title of the staff.
	*/
	private String jobTitle;
	/**
	 Create a new constructor for staff.
	*/
	public Staff(){}

	/**
	 * Creates a staff with given name, gender, id and job title.
	 * @param name This is the name of the staff.
	 * @param gender This is the gender of the staff.
	 * @param staffid This is the id of the staff.
	 * @param jobtitle This is the job title of the staff.
	 */
	public Staff(String name, String gender, int staffid, String jobtitle) {
		this.name = name;
		this.gender = gender;
		this.staffId = staffid;
		this.jobTitle = jobtitle;
	}
	/**
	 * Gets the staff name.
	 * @return the staff name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Gets the staff gender.
	 * @return the staff gender.
	 */
	public String getGender() {
		return this.gender;
	}
	/**
	 * Gets the staff id.
	 * @return the staff id.
	 */
	public int getStaffId() {
		return this.staffId;
	}
	/**
	 * Gets the staff job title.
	 * @return the staff job title.
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}
	/**
	 * Sets the staff name.
	 * @param name This set the staff name.
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Sets the staff gender.
	 * @param gender This set the staff gender.
	 */
	public void setGender(String gender){
		this.gender = gender;
	}
	/**
	 * Sets the staff id.
	 * @param staffId This set the staff id.
	 */
	public void setStaffId(int staffId){
		this.staffId = staffId;
	}
	/**
	 * Sets the job title.
	 * @param jobTitle This set the staff job title.
	 */
	public void setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}
}
