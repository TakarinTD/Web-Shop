$(document).ready(
		function() {
			$("#information-form").submit(function(event) {
				event.preventDefault();
				editInfo();
			});

			function editInfo() {

				var formData = {
					fullName : $("#fullName").val(),
					phone : $("#phone").val(),
					email : $("#email").val(),
					address : $("#address").val(),
				    birthday : $("#birthday").val(),
				    oldPassword : $("#oldPassword").val(),
				    password : $("#password").val(),
				    passwordConfirm : $("#passwordConfirm").val(),
				}

				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/my_account",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
                        $("#updateS").show()
                        $("#updateE").hide()
				        console.log(result)
					},
					error : function(e) {
					    $("#updateS").hide()
                        $("#updateE").show()
						console.log(e)
					}
				});
			}
		});