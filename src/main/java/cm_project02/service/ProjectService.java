package cm_project02.service;

import java.util.List;

import cm_project02.entity.Project;
import cm_project02.repository.ProjectRepository;

public class ProjectService {
// dựa vào người dùng muốn biết trả ra gì 
	private ProjectRepository projectReponsitory = new ProjectRepository();

// dựa trên repository khai báo gì trả lại gì thì service khai báo trả ra đó	
// trả ra boolean vì client muốn biết thành công hay thất bại
	public boolean insertProject(String name, String SDate, String EDate) {
		int count = projectReponsitory.insert(name, SDate, EDate);
		return count > 0;
	}
	public List<Project> getAllProject(){
		return projectReponsitory.findAll();
	}
	public List<Project> getAllProjectById(int id){
		return projectReponsitory.getAllProjectById(id);
	}
	public boolean updateProject(int id, String name, String startDate, String endDate) {
		int count = projectReponsitory.update(name, startDate, endDate, id);
		return count > 0;
	}
	public boolean deleteProject(int id) {
		int count = projectReponsitory.delete(id);
		return count > 0;
	}
}
