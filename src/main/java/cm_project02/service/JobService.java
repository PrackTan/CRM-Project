package cm_project02.service;

import java.util.List;

import cm_project02.entity.Job;
import cm_project02.repository.JobsRepository;

public class JobService {
	JobsRepository jobsReponsitory = new JobsRepository();
	
	public List<Job> getAllJob(){
		return jobsReponsitory.findAll();
	}
	public List<Job> getAllJobById(int id){
		return jobsReponsitory.getAllJobById(id);
	}
	public boolean insertJobs(String name, String projectName,String userName , String starDate, String endDate) {
		int count = jobsReponsitory.insert(name, projectName, userName, starDate, endDate);
		return count > 0;
	}
	public boolean updateJobs(String name, String projectName,String userName , String starDate, String endDate,int id) {
		int count = jobsReponsitory.update(name, projectName, userName, starDate, endDate, id);
		return count >0;
	}
	public boolean deleteJobs(int id) {
		int count = jobsReponsitory.delete(id);
		return count > 0;
	}
}
