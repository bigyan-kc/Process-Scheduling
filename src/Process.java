
public class Process {
	int arrival_time;
	float burst_time;
	float remain_time;
	float start_time;
	float turn_around_time;
	public float getStart_time() {
		return start_time;
	}
	public void setStart_time(float start_time) {
		this.start_time = start_time;
	}
	
	public float getTurn_around_time() {
		return turn_around_time;
	}
	public void setTurn_around_time(float turn_around_time) {
		this.turn_around_time = turn_around_time;
	}
	public float getWait_time() {
		return start_time;
	}
	public void setWait_time(float wait_time) {
		this.start_time = wait_time;
	}
	public float getRemain_time() {
		return remain_time;
	}
	public void setRemain_time(float remain_time) {
		this.remain_time = remain_time;
	}
	public int getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(int arrival_time) {
		this.arrival_time = arrival_time;
	}
	public float getBurst_time() {
		return burst_time;
	}
	public void setBurst_time(float burst_time) {
		this.burst_time = burst_time;
	}
	
}
