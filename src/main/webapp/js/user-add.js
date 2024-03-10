
$(document).ready(function(){
    // ...
    
 // Lắng nghe sự kiện "change" trên phần tử <select> có id "selectRole"
   $("#selectRole").change(function() {
        // Lấy giá trị của tùy chọn đã chọn
        var selectedOptionValue = $(this).val();
        
        // Hiển thị giá trị trong Console
        console.log(selectedOptionValue);
    });
    // Đăng ký sự kiện submit cho form thêm người dùng
    $("#add-user-form").submit(function(event) {
        event.preventDefault(); // Ngăn chặn form gửi dữ liệu mặc định
        // Lấy dữ liệu từ form và chuyển thành đối tượng JSON
        var selectedOptionValue
  		/* $("#selectRole").change(function() {
        // Lấy giá trị của tùy chọn đã chọn
       	 	selectedOptionValue = $(this).val();
   		 });*/


        var userData = {
            fullname: $("input[name='fullname']").val(),
            email: $("input[name='email']").val(),
            password: $("input[name='password']").val(),
            phone: $("input[name='phone']").val(),
            role: selectedOptionValue
        };
        // Gửi dữ liệu người dùng lên máy chủ bằng Ajax
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/crm_project02/api/user/add",
            data: JSON.stringify(userData), // Chuyển đối tượng thành chuỗi JSON
            contentType: "application/json", // Loại dữ liệu gửi đi
        })
        .done(function(result) {
            // Xử lý kết quả trả về từ máy chủ (nếu cần)
           // console.log(result)
            if (result.success) {
                // Thêm mã để thông báo thành công hoặc thực hiện hành động khác tùy theo trường hợp
                alert("User added successfully!");
            } else {
                // Xử lý khi có lỗi, ví dụ hiển thị thông báo lỗi
                alert("Error: " + result.message);
            }
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
            // Xử lý khi có lỗi Ajax
            console.log("Ajax error: " + errorThrown);
    		console.log("Status: " + textStatus);
    		console.log("Response: " + jqXHR.responseText);
        });
    });

    // ...
});

