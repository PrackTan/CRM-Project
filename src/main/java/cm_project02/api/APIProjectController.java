package cm_project02.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cm_project02.entity.Project;
import cm_project02.entity.Role;
import cm_project02.payload.reponse.BaseReponse;
import cm_project02.service.ProjectService;

@WebServlet(name="apiProjectController",urlPatterns = {"/api/project","/api/project/add","/api/project/delete","/api/project/update"})
public class APIProjectController extends HttpServlet{
	private Gson gson = new Gson();
	private ProjectService projectService = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/api/project/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isDelete = projectService.deleteProject(id);
			
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage(isDelete ? "xóa thành công" : "xóa thất bại");
			baseReponse.setData(isDelete);

			// chuyển qua format JSON bằng GSON
			String dataJSON = gson.toJson(baseReponse);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJSON);
		}else if(path.equals("/api/project")) {
			List<Project> listProject = projectService.getAllProject();
			
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("");
			baseReponse.setData(listProject);
			
			String dataJSON = gson.toJson(baseReponse);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJSON);

		}else if(path.equals("/api/role/update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			List<Project> listProjectById = projectService.getAllProjectById(id);
			
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("");
			baseReponse.setData(listProjectById);
			
			String dataJSON = gson.toJson(baseReponse);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJSON);
		
	
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/api/project")) {
				
				String Name = req.getParameter("name");
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");

				boolean isSucess = projectService.insertProject(Name, startDate, endDate);
			
				BaseReponse baseReponse = new BaseReponse();
				baseReponse.setStatuscode(200);
				baseReponse.setMessage(isSucess ? "Thêm thành công" : "Thêm thất bại");
				baseReponse.setData(isSucess);// gán giá trị cho từng thuộc tính baseReponse
				// chuyển qua format JSON bằng GSON
				String dataJSON = gson.toJson(baseReponse);
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				out.print(dataJSON);
		}
				
		else if(path.equals("/api/project/update")) {
				int id = Integer.parseInt(req.getParameter("id"));
				String Name = req.getParameter("name");
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				
				boolean isSuccess = projectService.updateProject(id, Name, startDate, endDate);
			
				
				BaseReponse baseReponse = new BaseReponse();
				baseReponse.setStatuscode(200);
				baseReponse.setMessage(isSuccess ? "cập nhật thành công" : "cập nhật thất bại");
				baseReponse.setData(isSuccess);// gán giá trị cho từng thuộc tính baseReponse

				// chuyển qua format JSON bằng GSON
				String dataJSON = gson.toJson(baseReponse);
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");

				out.print(dataJSON);
			
		}
	}
}
