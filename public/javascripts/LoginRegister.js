app.controller('loginRegister', ['$scope', '$http', '$filter', '$state', '$mdDialog', 'ShamayimFunctions','$mdToast','$rootScope',function($scope, $http, $filter, $state, $mdDialog,ShamayimFunctions,$mdToast,$rootScope) {
    $scope.userName = 'RobertDupont';
    $scope.firstName = "Robert";
    $scope.lastName = "Dupont";
    $scope.telephone;
    $scope.email = "Robert@gmail.com";
    $scope.password = "a";
    $scope.birthdate = 1-1-2001;

    // For login scope
    $scope.Username = 'Y.Nathan';
    $scope.Password = 'a';



    $scope.uploadFile = function(files) {
        var fd = new FormData();
        //Take the selected file
        fd.append("file", files[0]);

        $http.post("/upload/" + ShamayimFunctions.getCookie("username"), fd, {
            withCredentials: true,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        }).success(
            swal("Yeah!!!")
        ).error(
            swal("Oups! something wrong was hapening")
        );

    };


    $scope.register = function() {
        var userName = $scope.userName;
        var email = $scope.email;

        // Check if the name exist
        $http({
                method: 'GET',
                url: '/CHECK_USER_NAME/' + userName
            })
            .then(function successCallback(response) {
                    // Check if the email exist
                    $http({
                            method: 'GET',
                            url: '/CHECK_EMAIL/' + email
                        })
                        .then(function successCallback(response) {
                                var birthdateOrder = $filter('date')($scope.birthdate, 'yyyy-MM-dd');
                                ShamayimFunctions.setUserNameCookie("username", userName);
                                // Register new user
                                $http({
                                    method: 'POST',
                                    url: '/REGISTER/' + userName + '/' + $scope.firstName + '/' + $scope.lastName + '/' + $scope.telephone + '/' + $scope.email + '/' + $scope.password + '/' + birthdateOrder
                                }).then(
                                    function successCallback(response) {
                                        alert("Register successful!");
                                        ShamayimFunctions.setPermissionCookie(1);
                                        $rootScope.bIsLoged = true;
                                        $state.go('House')
                                    },
                                    function errorCallback(response) {
                                        alert(response.data);
                                    });
                            },
                            function errorCallback(response) {
                                $scope.email = '';
                                alert(response.data);
                            });
                },
                function errorCallback(response) {
                    alert(response.data);
                    $scope.userName = '';
                });
    };


    $scope.login = function() {
        var userName = $scope.Username;
        var password = $scope.Password;

        // Check if the name exist
        $http({
            method: 'POST',
            url: '/LOGIN/' + userName + '/' + password
        }).then(function successCallback(response) {
        var szPermission = response.data;
            if (szPermission != "-1") {
                $mdDialog.show(
                    $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#popupContainer')))
                    .clickOutsideToClose(true)
                    .title('Wellcom')
                    .textContent('Enjoy our services.')
                    .ariaLabel('Alert Dialog Demo')
                    .ok('Lets start!')
                );
                ShamayimFunctions.setUserNameCookie("username", userName);
                ShamayimFunctions.setPermissionCookie(szPermission);
                $rootScope.bIsLoged = true;
                // Go to the main application
                $state.go('House');
            }
        }, function error(response) {
            alert(response.data);
        });
    };



$scope.showCustomToast = function() {
        $mdToast.show({
          hideDelay   : 10000,
          position    : 'top right',
          height: '100% !important',
          controller  : 'loginRegister',
          templateUrl : 'template/lr.html'
        });
      };
      $scope.closeToast = function() {
              if (isDlgOpen) return;

              $mdToast
                .hide()
                .then(function() {
                  isDlgOpen = false;
                });
            };

            $scope.openMoreInfo = function(e) {
              if ( isDlgOpen ) return;
              isDlgOpen = true;

              $mdDialog
                .show($mdDialog
                  .alert()
                  .title('More info goes here.')
                  .textContent('Something witty.')
                  .ariaLabel('More info')
                  .ok('Got it')
                  .targetEvent(e)
                )
                .then(function() {
                  isDlgOpen = false;
                })
            };


                $scope.showToast2 = function() {

                  $mdToast.show(
                    $mdToast.testPreset()
                  );
                }





                // Dialog
                 $scope.status = '  ';
                  $scope.customFullscreen = false;






                  $scope.showAdvanced = function(ev) {
                    $mdDialog.show({
                      controller: 'loginRegister',
                      templateUrl: 'template/lr.html',
                      parent: angular.element(document.body),
                      targetEvent: ev,
                      clickOutsideToClose:true,
                      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
                    })
                    .then(function(answer) {
                      $scope.status = 'You said the information was "' + answer + '".';
                    }, function() {
                      $scope.status = 'You cancelled the dialog.';
                    });
                  };

                  $scope.showTabDialog = function(ev) {
                    $mdDialog.show({
                      controller: DialogController,
                      templateUrl: 'tabDialog.tmpl.html',
                      parent: angular.element(document.body),
                      targetEvent: ev,
                      clickOutsideToClose:true
                    })
                        .then(function(answer) {
                          $scope.status = 'You said the information was "' + answer + '".';
                        }, function() {
                          $scope.status = 'You cancelled the dialog.';
                        });
                  };

                  $scope.showPrerenderedDialog = function(ev) {
                    $mdDialog.show({
                      controller: DialogController,
                      contentElement: '#myDialog',
                      parent: angular.element(document.body),
                      targetEvent: ev,
                      clickOutsideToClose: true
                    });
                  };

                  function DialogController($scope, $mdDialog) {
                    $scope.hide = function() {
                      $mdDialog.hide();
                    };

                    $scope.cancel = function() {
                      $mdDialog.cancel();
                    };

                    $scope.answer = function(answer) {
                      $mdDialog.hide(answer);
                    };
                  }
}]);