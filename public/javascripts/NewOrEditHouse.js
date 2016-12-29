app.controller('neworedithouse', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', '$rootScope', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions, $rootScope) {
    $scope.files;
    $scope.strCaptionDragAndDrop = "Drag & drop files here...";
    // Just print kind of 'hay message'
    $scope.message = 'M. ' + ShamayimFunctions.getCookie("username");
    $scope.DebterName = "";
    $scope.house_id = "1";
    $scope.state = "israel";
    $scope.city = "jerusalem";
    $scope.street = "yirmiyahu";
    $scope.house_number = 32;
    $scope.house_kind = 3;
    $scope.number_of_rooms = 1;
    $scope.number_of_living_rooms = 1;
    $scope.number_of_kitchens = 1;
    $scope.number_of_bedrooms = 3;
    $scope.number_of_bathrooms = 5;
    $scope.location_kind = 5;
    $scope.comments = "nice area have fun";
    $scope.purchase_price;
    $scope.treatment_fees;
    $scope.renovation_fees;
    $scope.divers_fees;
    $scope.userName = ShamayimFunctions.getCookie("username");
    var houseName = "";
    var tempArr = [];
    var house = {
        "state": "israel",
        "city": "jerusalem",
        "street": "yirmiyahu",
        "house_number": "32",
        "house_kind": "1",
        "number_of_rooms": "3",
        "number_of_living_rooms": "1",
        "number_of_kitchens": "2",
        "number_of_bedrooms": "1",
        "number_of_bathrooms": "1",
        "location_kind": "dfg",
        "comments": "Nice area great place to have fun"
    }

    $scope.dataTabs = {
        selectedIndex: 0,
        secondLocked: true,
        thirdLocked: true,
        bottom: false
    };
    $scope.next = function () {
        $scope.dataTabs.selectedIndex = Math.min($scope.dataTabs.selectedIndex + 1, 2);
    };
    $scope.previous = function () {
        $scope.dataTabs.selectedIndex = Math.max($scope.dataTabs.selectedIndex - 1, 0);
    };

    // For the house
    $scope.houses = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            house: 'default'
        }
    };



    // Get information conserning the
    $http.get("/GET_LIST_OF_HOUSES")
        .then(function successCallback(response) {
                angular.forEach(response.data.houses, function (value, key) {
                    itemName = {
                        id: key,
                        house: value
                    }
                    tempArr.push(itemName);
                    $scope.houses.availableOptions.push(itemName.house);
                }, $scope.houses);
            },
            function error(response) {
                ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
            });

    $scope.setNewHouse = function () {
        house.state = $scope.state;
        house.city = $scope.city;
        house.street = $scope.street;
        house.house_number = $scope.house_number;
        house.house_kind = $scope.house_kind;
        house.number_of_rooms = $scope.number_of_rooms;
        house.number_of_living_rooms = $scope.number_of_living_rooms;
        house.number_of_kitchens = $scope.number_of_kitchens;
        house.number_of_bedrooms = $scope.number_of_bedrooms;
        house.number_of_bathrooms = $scope.number_of_bathrooms;
        house.location_kind = $scope.location_kind;
        house.comments = $scope.comments;


        var res = $http.post('/SET_NEW_HOUSE', house);
        res.success(function (data, status, headers, config) {
            $scope.dataTabs.secondLocked = false;
            $scope.dataTabs.thirdLocked = false;
            alert(data);
        });
        res.error(function (data, status, headers, config) {
            alert("failure message: " + JSON.stringify({
                data: data
            }));
        });


    }

        $scope.setNewHouseAddress = function () {
            house.state = $scope.state;
            house.city = $scope.city;
            house.street = $scope.street;
            house.house_number = $scope.house_number;
            house.house_kind = $scope.house_kind;


            var res = $http.post('/SET_NEW_HOUSE', house);
            res.success(function (data, status, headers, config) {
                $scope.dataTabs.secondLocked = false;
                $scope.dataTabs.thirdLocked = false;
                alert(data);
            });
            res.error(function (data, status, headers, config) {
                alert("failure message: " + JSON.stringify({
                    data: data
                }));
            });


        }


    // Logic methods section
    function getHouse(nHouseId) {
        // Get information conserning the house
        $http.get("/GET_HOUSE_BY_ID/" + nHouseId)
            .then(function successCallback(response) {
                    $scope.house_id = response.data.house.house_id;
                    $scope.state = response.data.house.state;
                    $scope.city = response.data.house.city;
                    $scope.street = response.data.house.street;
                    $scope.house_number = response.data.house.house_number;
                    $scope.house_kind = response.data.house.house_kind;
                    $scope.number_of_rooms = response.data.house.number_of_rooms;
                    $scope.number_of_living_rooms = response.data.house.number_of_living_rooms;
                    $scope.number_of_kitchens = response.data.house.number_of_kitchens;
                    $scope.number_of_bedrooms = response.data.house.number_of_bedrooms;
                    $scope.number_of_bathrooms = response.data.house.number_of_bathrooms;
                    $scope.location_kind = response.data.house.location_kind;
                    $scope.comments = response.data.house.comments;
                    $scope.purchase_price = response.data.house.purchase_price;
                    $scope.treatment_fees = response.data.house.treatment_fees;
                    $scope.renovation_fees = response.data.house.renovation_fees;
                    $scope.divers_fees = response.data.house.divers_fees;
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });
    }



    // Just check if there is a user name
    if (ShamayimFunctions.getCookie("username") == null) {
        // Go to the main application
        $state.go('wellcom');
    }

    $scope.$watch('houses.selectedOption', function (newVal, oldVal) {
        if (newVal != oldVal) {
            houseName = newVal;
            getHouse(newVal.house_id);

        }
    });
    $scope.$watch('files.length', function (newVal, oldVal) {
        console.log($scope.files);
    });

    // Upload File To The Server (House Pictures OR Some Else File)
    $scope.uploadFiles = function () {
        var formData = new FormData();
        angular.forEach($scope.files, function (obj) {
            formData.append('files[]', obj.lfFile);
        });

        $http.post("/SET_HOUSE_PICTURES/" + house.state + "_" + house.city + "_" + house.street + "_" + house.house_number, formData, {
            withCredentials: true,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        }).success(
            alert("Yeah!!!")
        ).error(
            alert("Oups! something wrong was hapening")
        );
    }
    $scope.isManager = function () {
        if (ShamayimFunctions.getPermissionCookie() == "0") {
            return true;
        } else {
            return false;
        }
    }


}]);
