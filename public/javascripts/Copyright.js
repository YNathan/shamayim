app.controller('Copyright', ['$scope', '$http', '$filter', '$state', '$mdDialog', '$mdSidenav', function ($scope, $http, $filter, $state, $mdDialog, $mdSidenav) {

    $scope.userName = getCookie("username");
    $scope.username = 'Developer';
    $scope.firstName = '';
    $scope.lastName = '';
    $scope.telephone = '052787878';
    $scope.email = 'ipsum@lorem.com';
    $scope.password = '';
    $scope.birthdate = '';
    $scope.userId = '';

    $scope.goToCopyright = function () {
        $state.go('Copyright');
    }
    $scope.toggleLeft = function () {
        $mdSidenav('left').toggle();
    }
    $scope.goToUserInformation = function () {
        $state.go('userInformation');
    }
    $scope.goToMain = function () {
        $state.go('Main');
    }
    $scope.goToGroups = function () {
        $state.go('Groups');
    }
// Get value from the cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
    }

    // Get information conserning the user
    $http.get("/GET_USER_INFORMATION/" + getCookie("username"))
        .then(function successCallback(response) {
                $scope.username = response.data.user[0].user_name;
                $scope.firstname = response.data.user[0].first_name;
                $scope.lastname = response.data.user[0].last_name;
                $scope.email = response.data.user[0].email;
                $scope.telephone = response.data.user[0].telephone;
                $scope.password = response.data.user[0].password;
                $scope.birthdate = response.data.user[0].birthdate;
                $scope.userId = response.data.user[0].user_id;


                var userInformationNiceDisplay = $scope.username + " : " +
                    $scope.firstname + " : " +
                    $scope.lastname + " : " +
                    $scope.email + " : " +
                    $scope.telephone + " : " +
                    $scope.password + " : " +
                    $scope.birthdate + " : " +
                    $scope.userId;


            },
            function error(response) {
                showAlert("Your attention please", response.data, "cant load user information");
            });

    function showAlert(title, textContent, ariaLabel) {
        // Appending dialog to document.body to cover sidenav in docs app
        // Modal dialogs should fully cover application
        // to prevent interaction outside of dialog
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title(title)
                .textContent(textContent)
                .ariaLabel(ariaLabel)
        );
    }


    // Will input the user name into the cookie
    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }

    $scope.uploadFile = function (files) {
        var fd = new FormData();
        //Take the selected file
        fd.append("file", files[0]);

        $http.post("/upload/" + getCookie("username"), fd, {
            withCredentials: true,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(
            swal("Yeah!!!")
        ).error(
            swal("Oups! something wrong was hapening")
        );

    };

    $scope.reedit = function () {
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
                                setUserNameCookie("username", userName);
                                // Register new user
                                $http({
                                    method: 'POST',
                                    url: '/REGISTER/' + userName + '/' + $scope.firstName + '/' + $scope.lastName + '/' + $scope.telephone + '/' + $scope.email + '/' + $scope.password + '/' + birthdateOrder
                                }).then(
                                    function successCallback(response) {
                                        alert("Register successful!");
                                        $state.go('Main')
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


    // For the groups
    $scope.groups = {
        availableDebtsOptions: [],
        selectedDebtsOption: {
            id: '1',
            Debtes: 'default'
        }
    };
// Get groups conserning the user
    $http.get("/GET_GROUPS_INFORMATION/" + getCookie("username"))
        .then(function (response) {
            $scope.groups = response.data.groups;
        });


    $scope.getOwnerGroup = function (szOwnerName) {
        // Get owner conserning the group
        $http.get("/GET_OWNER_INFORMATION/" +szOwnerName)
            .then(function (response) {
                $scope.username = response.data.user[0].user_name;
                $scope.email = response.data.user[0].email;
                $scope.telephone = response.data.user[0].telephone;
                showAlert("Owner Group Information", "User Name : " + $scope.username + ", Telephone : " + $scope.telephone + ", email : "+$scope.email);
            });
    }

}]);