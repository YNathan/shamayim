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
          hideDelay   : 3000,
          position    : 'top right',
          controller  : 'ToastCtrl',
          templateUrl : 'toast-template.html'
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

}]);