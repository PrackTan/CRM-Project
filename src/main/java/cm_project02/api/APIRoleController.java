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

import cm_project02.entity.Role;
import cm_project02.payload.reponse.BaseReponse;
import cm_project02.service.RoleService;

@WebServlet(name="apiRoleController",urlPatterns = {"/api/role","/api/role/add","/api/role/delete","/api/role/update"})
public class APIRoleController extends HttpServlet{
	private Gson gson = new Gson();
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/api/role/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isDelete = roleService.deleteRole(id);
			
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
		}else if(path.equals("/api/role")) {
			List<Role> listRole = roleService.getAllRole();
			
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("");
			baseReponse.setData(listRole);
			
			String dataJSON = gson.toJson(baseReponse);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJSON);

		}else if(path.equals("/api/role/update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			List<Role> listRoleById = roleService.getAllRoleById(id);
			
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("");
			baseReponse.setData(listRoleById);
			
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
		if (path.equals("/api/role")) {
				
				String roleName = req.getParameter("name");
				String roleDescription = req.getParameter("description");
				boolean isSucess = roleService.addRole(roleName, roleDescription);
			
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
				
		else if(path.equals("/api/user/update")) {
				int id = Integer.parseInt(req.getParameter("id"));
				String roleName = req.getParameter("name");
				String roleDescription = req.getParameter("description");
				boolean isSuccess = roleService.updateRole(roleName, roleDescription, id);
			
				
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
