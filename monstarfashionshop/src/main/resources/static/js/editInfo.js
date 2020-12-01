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
						console.log(e.responseJSON.message)
					}
				});
			}

			$("#password-form").submit(function(event) {
            				event.preventDefault();
            				editPassword();
            			});

            			function editPassword() {

            				var formData = {
            				    oldPassword : $("#oldPassword").val(),
            				    password : $("#password").val(),
            				    passwordConfirm : $("#passwordConfirm").val(),
            				}
            				console.log(formData.oldPassword)
                            var oldPassword = formData.oldPassword;
                            var passwordConfirm = formData.passwordConfirm
            				$.ajax({
            					type : "POST",
            					contentType : "application/json",
            					url : "/my_password?oldPassword=" + oldPassword +"&passwordConfirm="+passwordConfirm,
            					data : JSON.stringify(formData),
            					dataType : 'json',
            					success : function(result) {
            					    $("#errorN").hide();
            					    $("#error").hide();
            					    $("#oldE").hide();
                                    $("#updateS").show();
                                    $("#updateE").hide();
                                    $("#changePassword").hide();
                                    $("#change").prop("checked", false);
            				        console.log(result)
            					},
            					error : function(e) {
            					    if(e.responseJSON.message == "Password Error!") {
            					        $("#oldE").show();
            					    } else $("#oldE").hide();
            					    if(e.responseJSON.message == "Password Mismatch!"){
            					        $("#error").show();
            					    }else $("#error").hide();
            					    if(e.responseJSON.message == "Password required!"){
                                        $("#errorN").show();
                                    }else $("#errorN").hide();
            					    $("#updateS").hide()
                                    $("#updateE").show()
            						console.log(e.responseJSON.message)
            					}
            				});
            			}
		});