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

import cm_project02.entity.Job;
import cm_project02.entity.Users;
import cm_project02.payload.reponse.BaseReponse;
import cm_project02.service.JobService;
import cm_project02.service.UserService;

@WebServlet(name="apiProjectController",urlPatterns = {"/api/job","/api/job/add","/api/job/delete","/api/job/update"})
public class APIJobController extends HttpServlet {
	private JobService jobService = new JobService();//hởi tạo thư viện gson
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// kiểm tra người dùng gọi vào đường dẫn nào
		String path = req.getServletPath();
		switch (path) {
		case "/api/job": {
//			tạo list gán giá trị ở user vào
			List<Job> listJobs = jobService.getAllJob();
			// tạo base format json
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("ListJobs");
			baseReponse.setData(listJobs);// gán giá trị cho từng thuộc tính baseReponse
			// chuyển list hoặc mãng về JSON
			String dataJson = gson.toJson(baseReponse);
			// trả dữ liệu dạng json
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);
			break;
		}
		case "/api/job/delete": {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSucess = jobService.deleteJobs(id);

			// tạo base format json
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage(isSucess ? "xóa thành công" : "xóa thất bại");
			baseReponse.setData(isSucess);// gán giá trị cho từng thuộc tính baseReponse

			// chuyển qua format JSON bằng GSON
			String dataJSON = gson.toJson(baseReponse);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJSON);
			break;
		}
		case "/api/job/update":{
			int id = Integer.parseInt(req.getParameter("id"));
			List<Job> listJobsById =  jobService.getAllJobById(id);
			
			BaseReponse baseReponse = new BaseReponse();
			baseReponse.setStatuscode(200);
			baseReponse.setMessage("");
			baseReponse.setData(listJobsById);
			
			String dataJson = gson.toJson(baseReponse);
			// trả dữ liệu dạng json
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
//		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
			if (path.equals("/api/job")) {
				String jobName = req.getParameter("jobName");
				String projectName = req.getParameter("projectName");
				String userName = req.getParameter("userName");
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				boolean isSucess = jobService.insertJobs(jobName, projectName, userName, startDate, endDate);
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
					
			else if(path.equals("/api/job/update")) {
					int id = Integer.parseInt(req.getParameter("id"));
					String jobName = req.getParameter("jobName");
					String projectName = req.getParameter("projectName");
					String userName = req.getParameter("userName");
					String startDate = req.getParameter("startDate");
					String endDate = req.getParameter("endDate");
					boolean isSucess = jobService.updateJobs(jobName, projectName, userName, startDate, endDate, id);
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
	}
}
