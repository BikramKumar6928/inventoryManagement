package beans;

public class Manager {
	private String id;
	private Owner owner;

	public Manager(Owner owner){
		this.owner = owner;
	}



	public void maintainLedger(){

	}

	public boolean qualityCheck(){
		return true;
	}

	public void updateStatus(){

	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
