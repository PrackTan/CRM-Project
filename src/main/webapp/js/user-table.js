// khi nào file html được load thì thực hiện cái gì đó
$(document).ready(function(){	
	  /*  var apiUrl = "http://localhost:8080/crm_project02/api/user";
        $.ajax({
          url: apiUrl,
          method: "GET",
          success: function(response) {
            console.log(response);
          },
          error: function(error) {
            console.error(error);
          }
        })
		*/	  
	//		  const element = $("#user-table")
		//	  console.log(element)
			//  let htmlAdd = ``
			  //const length = result.data.length;
			  //for(let i = 0; i<length;i++){
				  //const dataProduct = data.data[i]
				  //console.log(dataProduct)
				  //htmlAdd +=` <tr>
                    //                        <td>${dataProduct.id}</td>
                      //                      <td>${dataProduct.firstname}</td>
                        //                    <td>${dataProduct.lastname}</td>
                          //                  <td>${dataProduct.username}</td>
                            //                <td>${dataProduct.role.name}</td>
                              //              <td>
                                //                <a href='<c:url value = "/user-update?id=${dataProduct.id}"/>'  class="btn btn-sm btn-primary" >Sửa</a>
									//			<a href="#" class="btn btn-sm btn-danger btn-delete" id-user="${item.id}">Xóa</a>
                                      //          <a href="user-details.html" class="btn btn-sm btn-info">Xem</a>
                                        //    </td>
                                        //</tr>`
			  //}
			  //element.innerHTML = htmlAdd;
	// đăng ký sự kiện click : $("tên_thẻ||tên_class||id_thẻ").click()
	//class => .
	// id => #
	// đăng ký sự kiện click cho toàn bộ class có tên là btn-delete
	 $(".btn-delete").click(function(){
		// lấy giá trị thuộc tính(attr) bên thẻ có giá trị class là .btn-delete
		//$(this) đại diện cho function đang thực thi
		var id = $(this).attr("id-user")
		var This = $(this)
		//RestTemplate, HttpClient
		$.ajax({
			  method: "GET",
			  url: `http://localhost:8080/crm_project02/api/user/delete?id=${id}`,// String literals ``
			  //data: { name: "John", location: "Boston" } //tham số data chỉ danh cho method= post
			})
			  .done(function( result ) {
				  // msg đại diện cho kết quả url trả về 
				  console.log(result)
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