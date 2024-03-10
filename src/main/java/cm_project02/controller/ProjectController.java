package cm_project02.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cm_project02.entity.Project;
import cm_project02.payload.reponse.BaseReponse;
import cm_project02.service.ProjectService;
// tạo đường dẫn URL cho web
@WebServlet(name = "ProjectController", urlPatterns = {"/groupwork-add","/groupwork"})
public class ProjectController extends HttpServlet {
	private Gson gson = new Gson();
	ProjectService projectService = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		// tạo view cho web dựa vào view thông qua abc.jsp
		case "/groupwork": 
			List<Project> listProject = new ArrayList<Project>();
			listProject = projectService.getAllProject();
			req.setAttribute("listProjects", listProject);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			break;
			
		case "/groupwork-add":
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/groupwork-add")) {
			//Lấy giá trị người dùng nhập vào ô click button submit
			String name = req.getParameter("group-name");
			String sData = req.getParameter("group-sdate");
			String eDate = req.getParameter("group-edate");
			// trả ra giao diện
			boolean isSucess = projectService.insertProject(name, sData, eDate);
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("List");
			baseReponse.setData(isSucess?"thêm thành công":"thêm thất bại");// gán giá trị cho từng thuộc tính baseReponse
			// chuyển list hoặc mãng về JSON
			String dataJson = gson.toJson(baseReponse);
			// trả dữ liệu dạng json
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			req.setAttribute("isSucess", dataJson);
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}

	}
	
}
