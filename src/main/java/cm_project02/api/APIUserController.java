package cm_project02.api;

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

import cm_project02.entity.Role;
import cm_project02.entity.Users;
import cm_project02.payload.reponse.BaseReponse;
import cm_project02.service.UserService;

//payload:
//reponse: chứa các class liên quan tới format JSON trả ra cho client
//request: chứa các class liên quan tới format JSON client truyền lên server

@WebServlet(name = "apiUserController", urlPatterns = { "/api/user", "/api/user/delete", "/api/user/add","/api/user/update" })
public class APIUserController extends HttpServlet {
	private UserService userService = new UserService();
	// khởi tạo thư viện gson
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// kiểm tra người dùng gọi vào đường dẫn nào
		String path = req.getServletPath();
		switch (path) {
		case "/api/user": {
//			tạo list gán giá trị ở user vào
			List<Users> listUsers = userService.getAllUsers();
			// tạo base format json
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("List");
			baseReponse.setData(listUsers);// gán giá trị cho từng thuộc tính baseReponse
			// chuyển list hoặc mãng về JSON
			String dataJson = gson.toJson(baseReponse);
			// trả dữ liệu dạng json
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);
			break;
		}
		case "/api/user/delete": {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSucess = userService.deleteById(id);

			// tạo base format json
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage(isSucess ? "xóa thành công" : "xóa thất bại");
			baseReponse.setData(isSucess);// gán giá trị cho từng thuộc tính baseReponse

			// chuyển qua format JSON bằng GSON
			// chuyển lisht hoặc mãng về JSON
			String dataJSON = gson.toJson(baseReponse);
			// Trả dữ liệu dang JSON
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJSON);
			break;
		}
		
		/// lấy dự liệu gán vào các cột cho update
		case "/api/user/update":{
			int id = Integer.parseInt(req.getParameter("id"));
			List<Users> listUserById = userService.getAllUsersById(id);
			
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("");
			baseReponse.setData(listUserById);
			
			String dataJson = gson.toJson(baseReponse);
			// trả dữ liệu dạng json
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);
			break;
		}
//		case "/api/user/add" :{
////			List<Users> listRole = new ArrayList<Users>();
////			 listRole = userService.getAllRole();
//			String fullname = req.getParameter("fullname");
//			String password = req.getParameter("password");
//			String phone = req.getParameter("phone");
//			String email = req.getParameter("email");
//			int idRole = Integer.parseInt(req.getParameter("role"));
//			boolean isSucess = userService.insertUser(fullname, email, email, phone, idRole);
//			 BaseReponse baseReponse = new BaseReponse();
//				baseReponse.setStatuscode(200);
//				baseReponse.setMessage(isSucess ? "Thêm thành công":"Thêm thất bại");
//				baseReponse.setData(isSucess);
//				String dataJSON = gson.toJson(baseReponse);
//				PrintWriter out = resp.getWriter();
//				resp.setContentType("application/json");
//				resp.setCharacterEncoding("UTF-8");
//				out.print(dataJSON);
//			break;
//		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
//		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
			if (path.equals("/api/user")) {
					String fullname = req.getParameter("fullname");
					String password = req.getParameter("password");
					String phone = req.getParameter("phone");
					String email = req.getParameter("email");
					int idRole = Integer.parseInt(req.getParameter("role"));
					boolean isSucess = userService.insertUser(fullname, email, password, phone, idRole);
					// tạo base format json
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
					String fullname = req.getParameter("fullname");
					String password = req.getParameter("password");
					String phone = req.getParameter("phone");
					String email = req.getParameter("email");
					int idRole = Integer.parseInt(req.getParameter("role"));
					boolean isSucess = userService.updateUser(fullname, email, password, phone, idRole, id);			
					BaseReponse baseReponse = new BaseReponse();
					baseReponse.setStatuscode(200);
					baseReponse.setMessage(isSucess ? "cập nhật thành công" : "cập nhật thất bại");
					baseReponse.setData(isSucess);// gán giá trị cho từng thuộc tính baseReponse

					// chuyển qua format JSON bằng GSON
					String dataJSON = gson.toJson(baseReponse);
					PrintWriter out = resp.getWriter();
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");

					out.print(dataJSON);
				
			}
			
		
//		//tạo listRole trống từ list entity
//		List<Role> list = new ArrayList<Role>();
//		// gán usergetAllrole cho list
//		list = userService.getAllRole();
//		req.setAttribute("listRole",list);
	}
}
