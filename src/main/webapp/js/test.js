  $(document).ready(function() {

        var apiUrl = "http://localhost:8080/crm_project02/api/user";
        $.ajax({
          url: apiUrl,
          method: "GET",
          success: function(response) {
            console.log(response);
          },
          error: function(error) {
            console.error(error);
          }
        });

    });