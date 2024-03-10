// khi nào file html được load thì thực hiện cái gì đó
$(document).ready(function(){
	// đăng ký sự kiện click : $("tên_thẻ||tên_class||id_thẻ").click()
	//class => .
	// id => #
	// đăng ký sự kiện click cho toàn bộ class có tên là btn-delete
	$(".btn-delete").click(function(){
		// lấy giá trị thuộc tính(attr) bên thẻ có giá trị class là .btn-delete
		//$(this) đại diện cho function đang thực thi
		var id = $(this).attr("id-role")
		var This = $(this)
		//RestTemplate, HttpClient
		$.ajax({
			  method: "GET",
			  url: `http://localhost:8080/crm_project02/api/role/delete?id=${id}`,// String literals ``
			  //data: { name: "John", location: "Boston" } //tham số data chỉ danh cho method= post
			})
			  .done(function( result ) {
				  // msg đại diện cho kết quả url trả về 
				  //lấy giá trị kiểu Object trong JS thì tên_biến.key
				if(result.data == true){
					alert("Xóa thành công")
					//.parent() => đi ra thẻ cha
					//.closest() => đi ra th cha đc chỉ định
					//.remove()=> xóa thẻ hiện tại
					This.closest('tr').remove()
				}
			  });
			  
	})
		


})