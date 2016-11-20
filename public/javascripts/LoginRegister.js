app.controller('wellcom', ['$scope', '$http', '$filter', '$state', '$mdDialog', function($scope, $http, $filter, $state, $mdDialog) {
    $scope.userName = 'RobertDupont';
    $scope.firstName = "Robert";
    $scope.lastName = "Dupont";
    $scope.telephone;
    $scope.email = "Robert@gmail.com";
    $scope.password = "a";
    $scope.birthdate = 01 / 01 / 1990;

    // For login scope
    $scope.Username = 'Y.Nathan';
    $scope.Password = 'a';

    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }

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

    $scope.uploadFile = function(files) {
        var fd = new FormData();
        //Take the selected file
        fd.append("file", files[0]);

        $http.post("/upload/" + getCookie("username"), fd, {
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

    var house = {
        "house": {
            "state": "ca",
            "city": "la",
            "street": "blalba",
            "house_number": "3",
            "house_kind": "1",
            "number_of_rooms": "3",
            "number_of_living_rooms": "1",
            "number_of_kitchens": "2",
            "number_of_bedrooms": "1",
            "number_of_bathrooms": "1",
            "location_kind": "dfg",
            "comments": "Nice area great place to have fun"
        }
    }

    var res = $http.post('/SET_NEW_HOUSE', house);
    res.success(function(data, status, headers, config) {
        alert(data);
    });
    res.error(function(data, status, headers, config) {
        alert("failure message: " + JSON.stringify({
            data: data
        }));
    });

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
                                setUserNameCookie("username", userName);
                                // Register new user
                                $http({
                                    method: 'POST',
                                    url: '/REGISTER/' + userName + '/' + $scope.firstName + '/' + $scope.lastName + '/' + $scope.telephone + '/' + $scope.email + '/' + $scope.password + '/' + birthdateOrder
                                }).then(
                                    function successCallback(response) {
                                        alert("Register successful!");
                                        $state.go('Houses')
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
            if (response.data == "true") {
                $mdDialog.show(
                    $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#popupContainer')))
                    .clickOutsideToClose(true)
                    .title('Wellcom')
                    .textContent('Enjoy our services.')
                    .ariaLabel('Alert Dialog Demo')
                    .ok('Lets start!')
                );
                setUserNameCookie("username", userName);
                // Go to the main application
                $state.go('Houses');
            }
        }, function error(response) {
            alert(response.data);
        });
    };



}]);