
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class RoundRobin {

	public static void main(String[] args) {
		String fileName="input.txt";
//		String fileName="testinput.txt";
		int time_quantum[]= {50,100,250,500};
		int overhead_time[]= {0,5,10,15,20,25};
		for(int time_quantum_i=0;time_quantum_i<time_quantum.length;time_quantum_i++) {
			for(int overhead_i=0;overhead_i<overhead_time.length;overhead_i++) {
				List<Process> processList=new ArrayList<Process>();
		try{
		Scanner scanner=new Scanner(new File(fileName));
		while(scanner.hasNext()){
			Process process=new Process();
			process.setArrival_time(scanner.nextInt());
			float burst_time=scanner.nextFloat();
			process.setBurst_time(burst_time);
			process.setRemain_time(burst_time);
			process.setStart_time(-1);
			processList.add(process);
		}
		scanner.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
				findTime(processList,time_quantum[time_quantum_i],overhead_time[overhead_i]);
			}
		}
	}
	static int findTime(List<Process> allProcesses,int quantum_time,int overhead_time) {
			
			Queue<Process>readyQueue=new LinkedList<Process>();
			List<Process>finishedProcesses=new ArrayList<Process>();
			int listIndex=0,sumQueueLength=0,maxQueueLength=1,queueCount=0;
			float avgWait,avgTurnAround,avgQueueLength,waitTime=0,turnAroundTime=0;
			float time_counter=allProcesses.get(0).getArrival_time();
			boolean isProcessComplete=false;
			readyQueue.add(allProcesses.get(0));
			++listIndex;
			while(readyQueue.size()!=0) {
				Process tempProcess=readyQueue.poll();
				
				if(tempProcess.getRemain_time()>quantum_time) {
					tempProcess.setRemain_time(tempProcess.getRemain_time()-quantum_time);
					if(tempProcess.getWait_time()==-1) {
						tempProcess.setWait_time(time_counter);
					}
					time_counter+=quantum_time+overhead_time;
				}
				else {
					isProcessComplete=true;
					if(tempProcess.getWait_time()==-1) {
						tempProcess.setWait_time(time_counter);
					}
					time_counter+=tempProcess.getRemain_time()+overhead_time;
					tempProcess.setTurn_around_time(time_counter);
					tempProcess.setRemain_time(0);
				}
				while(listIndex<allProcesses.size()&&time_counter>=allProcesses.get(listIndex).getArrival_time()) {
					readyQueue.add(allProcesses.get(listIndex));
					++listIndex;
				}
				if(!isProcessComplete) {
					readyQueue.add(tempProcess);
				}
				else if(isProcessComplete) {
					finishedProcesses.add(tempProcess);
				}
				if(listIndex<allProcesses.size()&&readyQueue.size()==0) {
				time_counter=allProcesses.get(listIndex).getArrival_time();
				readyQueue.add(allProcesses.get(listIndex));
			}

					isProcessComplete=false;
					if(readyQueue.size()>maxQueueLength)
						maxQueueLength=readyQueue.size();
					sumQueueLength+=readyQueue.size();
					++queueCount;
			}
			
			for(int process_i=0;process_i<finishedProcesses.size();process_i++) {
				waitTime+=finishedProcesses.get(process_i).getWait_time()-finishedProcesses.get(process_i).getArrival_time();

				turnAroundTime+=finishedProcesses.get(process_i).getTurn_around_time()-finishedProcesses.get(process_i).getArrival_time();
			}
			avgWait=waitTime/finishedProcesses.size();
			avgTurnAround=turnAroundTime/finishedProcesses.size();
			avgQueueLength=sumQueueLength/queueCount;
			System.out.println("Results for quantum_time "+quantum_time+" and overhead time "+overhead_time+":");
			System.out.println("Total Length of Simulation: "+time_counter);
			System.out.println("Average Wait time: "+avgWait);
			System.out.println("Average turnaround time: "+avgTurnAround);
			System.out.println("Max Queue Length: "+maxQueueLength);
			System.out.println("Average Queue Length: "+avgQueueLength);
			System.out.println();
			return 0;
			}
}
