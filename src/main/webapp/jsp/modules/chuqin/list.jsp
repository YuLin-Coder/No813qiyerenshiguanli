<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">


<head>
    <%@ include file="../../static/head.jsp" %>
    <!-- font-awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">

</head>
<style>

</style>
<body>
<!-- Pre Loader -->
<div class="loading">
    <div class="spinner">
        <div class="double-bounce1"></div>
        <div class="double-bounce2"></div>
    </div>
</div>
<!--/Pre Loader -->
<div class="wrapper">
    <!-- Page Content -->
    <div id="content">
        <!-- Top Navigation -->
        <%@ include file="../../static/topNav.jsp" %>
        <!-- Menu -->
        <div class="container menu-nav">
            <nav class="navbar navbar-expand-lg lochana-bg text-white">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="ti-menu text-white"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul id="navUl" class="navbar-nav mr-auto">
                    </ul>
                </div>
            </nav>
        </div>
        <!-- /Menu -->
        <!-- Breadcrumb -->
        <!-- Page Title -->
        <div class="container mt-0">
            <div class="row breadcrumb-bar">
                <div class="col-md-6">
                    <h3 class="block-title">考勤管理</h3>
                </div>
                <div class="col-md-6">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/index.jsp">
                                <span class="ti-home"></span>
                            </a>
                        </li>
                        <li class="breadcrumb-item">考勤管理</li>
                        <li class="breadcrumb-item active">考勤列表</li>
                    </ol>
                </div>
            </div>
        </div>
        <!-- /Page Title -->

        <!-- /Breadcrumb -->
        <!-- Main Content -->
        <div class="container">

            <div class="row">
                <!-- Widget Item -->
                <div class="col-md-12">
                    <div class="widget-area-2 lochana-box-shadow">
                        <h3 class="widget-title">考勤列表</h3>
                        <div class="table-responsive mb-3">
                            <div class="col-sm-12">
                                                                                                                                                                                                 
                                            <label>
                                                打卡类型
                                                <select name="chuqinTypesSelectSearch" id="chuqinTypesSelectSearch" class="form-control form-control-sm"
                                                        aria-controls="tableId">
                                                </select>
                                            </label>
                                                                
                                                                                                                                                                                 
                                        <label>
                                            姓名
                                            <input type="text" id="yonghuNameSearch" class="form-control form-control-sm"
                                                   placeholder="" aria-controls="tableId">
                                        </label>
                                             
                                        <label>
                                            身份证号
                                            <input type="text" id="yonghuIdNumberSearch" class="form-control form-control-sm"
                                                   placeholder="" aria-controls="tableId">
                                        </label>

                                        <label>
                                            权限
                                            <select name="roleTypesSelectSearch"  style="width: 150px;" id="roleTypesSelectSearch" class="form-control form-control-sm"
                                                    aria-controls="tableId">
                                            </select>
                                        </label>

                                        <label>
                                            上班打卡时间
                                            <input type="time" id="onTimeStartSearch" class="form-control " placeholder="开始" aria-controls="tableId">
                                        </label>
                                            -
                                        <label>
                                            <input type="time" id="onTimeEndSearch" class="form-control" placeholder="结束" aria-controls="tableId">
                                        </label>
                                        <label>
                                            下班打卡时间
                                            <input type="time" id="downTimeStartSearch" class="form-control " placeholder="开始" aria-controls="tableId">
                                        </label>
                                        -
                                        <label>
                                            <input type="time" id="downTimeEndSearch" class="form-control" placeholder="结束" aria-controls="tableId">
                                        </label>
                                            
                                <button onclick="search()" type="button" class="btn btn-primary">查询</button>
                                <button onclick="add()" type="button" class="btn btn-success 新增">添加</button>
                                <button onclick="deleteMore()" type="button" class="btn btn-danger 删除">批量删除</button>
                                <button onclick="graph()" type="button" class="btn btn-success 报表">报表</button>
                                <button onclick="clockIn(1)" type="button" class="btn btn-success clockIn">上班打卡</button>
                                <button onclick="clockIn(2)" type="button" class="btn btn-success clockIn">下班打卡</button>
                            </div>
                            <table id="tableId" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th class="no-sort" style="min-width: 35px;">
                                        <div class="custom-control custom-checkbox">
                                            <input class="custom-control-input" type="checkbox" id="select-all"
                                                   onclick="chooseAll()">
                                            <label class="custom-control-label" for="select-all"></label>
                                        </div>
                                    </th>

                                    <th >姓名</th>
                                    <th >身份证号</th>
                                    <th >权限</th>
                                    <th >上班打卡时间</th>
                                    <th >下班打卡时间</th>
                                    <th >打卡类型</th>
                                    <th >加班时间(小时)</th>

                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <div class="col-md-6 col-sm-3">
                                <div class="dataTables_length" id="tableId_length">

                                    <select name="tableId_length" aria-controls="tableId" id="selectPageSize"
                                            onchange="changePageSize()">
                                        <option value="10">10</option>
                                        <option value="25">25</option>
                                        <option value="50">50</option>
                                        <option value="100">100</option>
                                    </select>
                                    条 每页

                                </div>
                            </div>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-end">
                                    <li class="page-item" id="tableId_previous" onclick="pageNumChange('pre')">
                                        <a class="page-link" href="#" tabindex="-1">上一页</a>
                                    </li>

                                    <li class="page-item" id="tableId_next" onclick="pageNumChange('next')">
                                        <a class="page-link" href="#">下一页</a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- /Widget Item -->
            </div>
        </div>
        <!-- /Main Content -->

    </div>
    <!-- /Page Content -->
</div>
<!-- Back to Top -->
<a id="back-to-top" href="#" class="back-to-top">
    <span class="ti-angle-up"></span>
</a>
<!-- /Back to Top -->
<%@ include file="../../static/foot.jsp" %>
<script language="javascript" type="text/javascript"
        src="${pageContext.request.contextPath}/resources/My97DatePicker/WdatePicker.js"></script>
<script>
    <%@ include file="../../utils/menu.jsp"%>
            <%@ include file="../../static/setMenu.js"%>
            <%@ include file="../../utils/baseUrl.jsp"%>
            <%@ include file="../../static/getRoleButtons.js"%>
            <%@ include file="../../static/crossBtnControl.js"%>
    var tableName = "chuqin";
    var pageType = "list";
    var searchForm = {key: ""};
    var pageIndex = 1;
    var pageSize = 10;
    var totalPage = 0;
    var dataList = [];
    var sortColumn = '';
    var sortOrder = '';
    var ids = [];
    var checkAll = false;

     var zhiwuTypesOptions = [];
     var zhichengTypesOptions = [];
     var sexTypesOptions = [];
     var educationTypesOptions = [];

    var chuqinTypesOptions = [];

    function init() {
        // 满足条件渲染提醒接口
    }

    // 改变每页记录条数
    function changePageSize() {
        var selection = document.getElementById('selectPageSize');
        var index = selection.selectedIndex;
        pageSize = selection.options[index].value;
        getDataList();
    }



    // 查询
    function search() {
        searchForm = {key: ""};

        <!-- 级联表的级联字典表 -->
                                                                                 
                        //姓名
        var yonghuNameSearchInput = $('#yonghuNameSearch');
        if( yonghuNameSearchInput != null){
            if (yonghuNameSearchInput.val() != null && yonghuNameSearchInput.val() != '') {
                searchForm.yonghuName = $('#yonghuNameSearch').val();
            }
        }
                     
                        //身份证号
        var yonghuIdNumberSearchInput = $('#yonghuIdNumberSearch');
        if( yonghuIdNumberSearchInput != null){
            if (yonghuIdNumberSearchInput.val() != null && yonghuIdNumberSearchInput.val() != '') {
                searchForm.yonghuIdNumber = $('#yonghuIdNumberSearch').val();
            }
        }


        //权限
        var roleTypesSelectSearchInput = document.getElementById("roleTypesSelectSearch");
        if(roleTypesSelectSearchInput != null){
            var roleTypesIndex = roleTypesSelectSearchInput.selectedIndex;
            if( roleTypesIndex  != 0){
                searchForm.roleTypes= document.getElementById("roleTypesSelectSearch").options[roleTypesIndex].value;
            }
        }

                        <!-- 本表的查询条件 -->
                         
            //打卡类型
        var chuqinTypesSelectSearchInput = document.getElementById("chuqinTypesSelectSearch");
        if(chuqinTypesSelectSearchInput != null){
            var chuqinTypesIndex = chuqinTypesSelectSearchInput.selectedIndex;
            if( chuqinTypesIndex  != 0){
                searchForm.chuqinTypes = document.getElementById("chuqinTypesSelectSearch").options[chuqinTypesIndex].value;
            }
        }


        var onTimeStartSearch = $('#onTimeStartSearch');
        if( onTimeStartSearch != null){
            if (onTimeStartSearch.val() != null && onTimeStartSearch.val() != '') {
                searchForm.onTimeStart = $('#onTimeStartSearch').val();
            }
        }
        var onTimeEndSearch = $('#onTimeEndSearch');
        if( onTimeEndSearch != null){
            if (onTimeEndSearch.val() != null && onTimeEndSearch.val() != '') {
                searchForm.onTimeEnd = $('#onTimeEndSearch').val();
            }
        }

        var downTimeStartSearch = $('#downTimeStartSearch');
        if( downTimeStartSearch != null){
            if (downTimeStartSearch.val() != null && downTimeStartSearch.val() != '') {
                searchForm.downTimeStart = $('#downTimeStartSearch').val();
            }
        }
        var downTimeEndSearch = $('#downTimeEndSearch');
        if( downTimeEndSearch != null){
            if (downTimeEndSearch.val() != null && downTimeEndSearch.val() != '') {
                searchForm.downTimeEnd = $('#downTimeEndSearch').val();
            }
        }
                getDataList();
    }


    /**
     * 打卡
     */
    function clockIn(b){
        var flag="";
        if(b ==null || b =="" || b =="null"){
            alert("异常操作");
        }else if(b =="1"){
            flag="上班打卡成功";
        }else if(b =="2"){
            flag="下班打卡成功";
        }else{
            flag="未知";
        }
        http("chuqin/clockIn", "GET", {
            //本表的
            flag: b,
        }, (res) => {
            if(res.code == 0){
                alert(flag);
                getDataList();
            }else{
                alert("打卡失败,请联系管理查看问题");
                alert(res.msg);
            }
        });
    }
    // 获取数据列表
    function getDataList() {
        http("chuqin/page", "GET", {
            page: pageIndex,
            limit: pageSize,
            sort: sortColumn,
            order: sortOrder,
            //本表的
            chuqinTypes: searchForm.chuqinTypes,
            onTimeStart: searchForm.onTimeStart,
            onTimeEnd: searchForm.onTimeEnd,
            downTimeStart: searchForm.downTimeStart,
            downTimeEnd: searchForm.downTimeEnd,

            //级联表的
            yonghuName: searchForm.yonghuName,
            zhiwuTypes: searchForm.zhiwuTypes,
            zhichengTypes: searchForm.zhichengTypes,
            yonghuIdNumber: searchForm.yonghuIdNumber,
            sexTypes: searchForm.sexTypes,
            educationTypes: searchForm.educationTypes,
            roleTypes: searchForm.roleTypes,


    }, (res) => {
            if(res.code == 0
            )
            {
                clear();
                dataList = res.data.list;
                totalPage = res.data.totalPage;
                //var tbody = document.getElementById('tbMain');
                for (var i = 0; i < dataList.length; i++) { //遍历一下表格数据  
                    var trow = setDataRow(dataList[i], i); //定义一个方法,返回tr数据 
                    $('tbody').append(trow);
                }
                pagination(); //渲染翻页组件
                getRoleButtons();// 权限按钮控制
            }
        });
    }

    // 渲染表格数据
    function setDataRow(item, number) {
        //创建行 
        var row = document.createElement('tr');
        row.setAttribute('class', 'useOnce');
        //创建勾选框
        var checkbox = document.createElement('td');
        var checkboxDiv = document.createElement('div');
        checkboxDiv.setAttribute("class", "custom-control custom-checkbox");
        var checkboxInput = document.createElement('input');
        checkboxInput.setAttribute("class", "custom-control-input");
        checkboxInput.setAttribute("type", "checkbox");
        checkboxInput.setAttribute('name', 'chk');
        checkboxInput.setAttribute('value', item.id);
        checkboxInput.setAttribute("id", number);
        checkboxDiv.appendChild(checkboxInput);
        var checkboxLabel = document.createElement('label');
        checkboxLabel.setAttribute("class", "custom-control-label");
        checkboxLabel.setAttribute("for", number);
        checkboxDiv.appendChild(checkboxLabel);
        checkbox.appendChild(checkboxDiv);
        row.appendChild(checkbox)


                //姓名
        var yonghuNameCell = document.createElement('td');
        yonghuNameCell.innerHTML = item.yonghuName;
        row.appendChild(yonghuNameCell);


                //身份证号
        var yonghuIdNumberCell = document.createElement('td');
        yonghuIdNumberCell.innerHTML = item.yonghuIdNumber;
        row.appendChild(yonghuIdNumberCell);


        //权限
        var roleTypesCell = document.createElement('td');
        roleTypesCell.innerHTML = item.roleValue;
        row.appendChild(roleTypesCell);



        //上班打卡时间
        var onTimeCell = document.createElement('td');
        onTimeCell.innerHTML = item.onTime;
        row.appendChild(onTimeCell);

        //下班打卡时间
        var downTimeCell = document.createElement('td');
        downTimeCell.innerHTML = item.downTime;
        row.appendChild(downTimeCell);


        //打卡类型
        var chuqinTypesCell = document.createElement('td');
        chuqinTypesCell.innerHTML = item.chuqinValue;
        row.appendChild(chuqinTypesCell);


        //加班时间(小时)
        var overtimeNumberCell = document.createElement('td');
        if(item.overtimeNumber == null){
            overtimeNumberCell.innerHTML = "空";
        }else{
            overtimeNumberCell.innerHTML = item.overtimeNumber;
        }
        row.appendChild(overtimeNumberCell);



        //每行按钮
        var btnGroup = document.createElement('td');

        //详情按钮
        var detailBtn = document.createElement('button');
        var detailAttr = "detail(" + item.id + ')';
        detailBtn.setAttribute("type", "button");
        detailBtn.setAttribute("class", "btn btn-info btn-sm 查看");
        detailBtn.setAttribute("onclick", detailAttr);
        detailBtn.innerHTML = "查看"
        btnGroup.appendChild(detailBtn)

        //主管 不能修改和删除自己的
        if (window.sessionStorage.getItem('role') == "部门主管" && window.sessionStorage.getItem('userId') != item.yonghuId) {
            //修改按钮
            var editBtn = document.createElement('button');
            var editAttr = 'edit(' + item.id + ')';
            editBtn.setAttribute("type", "button");
            editBtn.setAttribute("class", "btn btn-warning btn-sm 修改");
            editBtn.setAttribute("onclick", editAttr);
            editBtn.innerHTML = "修改"
            btnGroup.appendChild(editBtn)
            //删除按钮
            var deleteBtn = document.createElement('button');
            var deleteAttr = 'remove(' + item.id + ')';
            deleteBtn.setAttribute("type", "button");
            deleteBtn.setAttribute("class", "btn btn-danger btn-sm 删除");
            deleteBtn.setAttribute("onclick", deleteAttr);
            deleteBtn.innerHTML = "删除"
            btnGroup.appendChild(deleteBtn)
        }

        row.appendChild(btnGroup)
        return row;
    }


    // 翻页
    function pageNumChange(val) {
        if (val == 'pre') {
            pageIndex--;
        } else if (val == 'next') {
            pageIndex++;
        } else {
            pageIndex = val;
        }
        getDataList();
    }

    // 下载
    function download(url) {
        window.open(url);
    }

    // 渲染翻页组件
    function pagination() {
        var beginIndex = pageIndex;
        var endIndex = pageIndex;
        var point = 4;
        //计算页码
        for (var i = 0; i < 3; i++) {
            if (endIndex == totalPage) {
                break;
            }
            endIndex++;
            point--;
        }
        for (var i = 0; i < 3; i++) {
            if (beginIndex == 1) {
                break;
            }
            beginIndex--;
            point--;
        }
        if (point > 0) {
            while (point > 0) {
                if (endIndex == totalPage) {
                    break;
                }
                endIndex++;
                point--;
            }
            while (point > 0) {
                if (beginIndex == 1) {
                    break;
                }
                beginIndex--;
                point--
            }
        }
        // 是否显示 前一页 按钮
        if (pageIndex > 1) {
            $('#tableId_previous').show();
        } else {
            $('#tableId_previous').hide();
        }
        // 渲染页码按钮
        for (var i = beginIndex; i <= endIndex; i++) {
            var pageNum = document.createElement('li');
            pageNum.setAttribute('onclick', "pageNumChange(" + i + ")");
            if (pageIndex == i) {
                pageNum.setAttribute('class', 'paginate_button page-item active useOnce');
            } else {
                pageNum.setAttribute('class', 'paginate_button page-item useOnce');
            }
            var pageHref = document.createElement('a');
            pageHref.setAttribute('class', 'page-link');
            pageHref.setAttribute('href', '#');
            pageHref.setAttribute('aria-controls', 'tableId');
            pageHref.setAttribute('data-dt-idx', i);
            pageHref.setAttribute('tabindex', 0);
            pageHref.innerHTML = i;
            pageNum.appendChild(pageHref);
            $('#tableId_next').before(pageNum);
        }
        // 是否显示 下一页 按钮
        if (pageIndex < totalPage) {
            $('#tableId_next').show();
            $('#tableId_next a').attr('data-dt-idx', endIndex + 1);
        } else {
            $('#tableId_next').hide();
        }
        var pageNumInfo = "当前第 " + pageIndex + " 页，共 " + totalPage + " 页";
        $('#tableId_info').html(pageNumInfo);
    }

    // 跳转到指定页
    function toThatPage() {
        //var index = document.getElementById('pageIndexInput').value;
        if (index < 0 || index > totalPage) {
            alert('请输入正确的页码');
        } else {
            pageNumChange(index);
        }
    }

    // 全选/全不选
    function chooseAll() {
        checkAll = !checkAll;
        var boxs = document.getElementsByName("chk");
        for (var i = 0; i < boxs.length; i++) {
            boxs[i].checked = checkAll;
        }
    }

    // 批量删除
    function deleteMore() {
        ids = []
        var boxs = document.getElementsByName("chk");
        for (var i = 0; i < boxs.length; i++) {
            if (boxs[i].checked) {
                ids.push(boxs[i].value)
            }
        }
        if (ids.length == 0) {
            alert('请勾选要删除的记录');
        } else {
            remove(ids);
        }
    }

    // 删除
    function remove(id) {
        var mymessage = confirm("真的要删除吗？");
        if (mymessage == true) {
            var paramArray = [];
            if (id == ids) {
                paramArray = id;
            } else {
                paramArray.push(id);
            }
            httpJson("chuqin/delete", "POST", paramArray, (res) => {
                if(res.code == 0
        )
            {
                getDataList();
                alert('删除成功');
            }
        })
            ;
        }
        else {
            alert("已取消操作");
        }
    }

    // 用户登出
    <%@ include file="../../static/logout.jsp"%>

    //修改
    function edit(id) {
        window.sessionStorage.setItem('updateId', id)
        window.location.href = "add-or-update.jsp"
    }

    //清除会重复渲染的节点
    function clear() {
        var elements = document.getElementsByClassName('useOnce');
        for (var i = elements.length - 1; i >= 0; i--) {
            elements[i].parentNode.removeChild(elements[i]);
        }
    }

    //添加
    function add() {
        window.sessionStorage.setItem("addchuqin", "addchuqin");
        window.location.href = "add-or-update.jsp"
    }

    //报表
    function graph() {
        window.location.href = "graph.jsp"
    }

    // 查看详情
    function detail(id) {
        window.sessionStorage.setItem("updateId", id);
        window.location.href = "info.jsp";
    }

    //填充级联表搜索下拉框
                                                                                 
                                         
        function zhiwuTypesSelectSearch() {
            var zhiwuTypesSelectSearch = document.getElementById('zhiwuTypesSelectSearch');
            if(zhiwuTypesSelectSearch != null) {
                zhiwuTypesSelectSearch.add(new Option('', ''));
                if (zhiwuTypesOptions != null && zhiwuTypesOptions.length > 0){
                    for (var i = 0; i < zhiwuTypesOptions.length; i++) {
                            zhiwuTypesSelectSearch.add(new Option(zhiwuTypesOptions[i].indexName, zhiwuTypesOptions[i].codeIndex));
                    }
                }
            }
        }
                     
        function zhichengTypesSelectSearch() {
            var zhichengTypesSelectSearch = document.getElementById('zhichengTypesSelectSearch');
            if(zhichengTypesSelectSearch != null) {
                zhichengTypesSelectSearch.add(new Option('', ''));
                if (zhichengTypesOptions != null && zhichengTypesOptions.length > 0){
                    for (var i = 0; i < zhichengTypesOptions.length; i++) {
                            zhichengTypesSelectSearch.add(new Option(zhichengTypesOptions[i].indexName, zhichengTypesOptions[i].codeIndex));
                    }
                }
            }
        }
                     
                     
        function sexTypesSelectSearch() {
            var sexTypesSelectSearch = document.getElementById('sexTypesSelectSearch');
            if(sexTypesSelectSearch != null) {
                sexTypesSelectSearch.add(new Option('', ''));
                if (sexTypesOptions != null && sexTypesOptions.length > 0){
                    for (var i = 0; i < sexTypesOptions.length; i++) {
                            sexTypesSelectSearch.add(new Option(sexTypesOptions[i].indexName, sexTypesOptions[i].codeIndex));
                    }
                }
            }
        }
                                                                                                                                             
        function educationTypesSelectSearch() {
            var educationTypesSelectSearch = document.getElementById('educationTypesSelectSearch');
            if(educationTypesSelectSearch != null) {
                educationTypesSelectSearch.add(new Option('', ''));
                if (educationTypesOptions != null && educationTypesOptions.length > 0){
                    for (var i = 0; i < educationTypesOptions.length; i++) {
                            educationTypesSelectSearch.add(new Option(educationTypesOptions[i].indexName, educationTypesOptions[i].codeIndex));
                    }
                }
            }
        }

    function roleTypesSelectSearch() {
        var roleTypesSelectSearch = document.getElementById('roleTypesSelectSearch');
        if(roleTypesSelectSearch != null) {
            roleTypesSelectSearch.add(new Option('-请选择-',''));
            if (roleTypesOptions != null && roleTypesOptions.length > 0){
                for (var i = 0; i < roleTypesOptions.length; i++) {
                    roleTypesSelectSearch.add(new Option(roleTypesOptions[i].indexName,roleTypesOptions[i].codeIndex));
                }
            }
        }
    }
                    
    //填充本表搜索下拉框
                         
        function chuqinTypesSelectSearch() {
            var chuqinTypesSelectSearch = document.getElementById('chuqinTypesSelectSearch');
            if(chuqinTypesSelectSearch != null) {
                chuqinTypesSelectSearch.add(new Option('-请选择-',''));
                if (chuqinTypesOptions != null && chuqinTypesOptions.length > 0){
                    for (var i = 0; i < chuqinTypesOptions.length; i++) {
                            chuqinTypesSelectSearch.add(new Option(chuqinTypesOptions[i].indexName,chuqinTypesOptions[i].codeIndex));
                    }
                }
            }
        }
        
    //查询级联表搜索条件所有列表
        function zhiwuTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=zhiwu_types", "GET", {}, (res) => {
                if(res.code == 0){
                    zhiwuTypesOptions = res.data.list;
                }
            });
        }
        function zhichengTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=zhicheng_types", "GET", {}, (res) => {
                if(res.code == 0){
                    zhichengTypesOptions = res.data.list;
                }
            });
        }
        function sexTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=sex_types", "GET", {}, (res) => {
                if(res.code == 0){
                    sexTypesOptions = res.data.list;
                }
            });
        }
        function educationTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=education_types", "GET", {}, (res) => {
                if(res.code == 0){
                    educationTypesOptions = res.data.list;
                }
            });
        }

        function roleTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=role_types", "GET", {}, (res) => {
                if(res.code == 0){
                roleTypesOptions = res.data.list;
            }
        });
        }


    //查询当前表搜索条件所有列表
        function chuqinTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=chuqin_types", "GET", {}, (res) => {
                if(res.code == 0){
                    chuqinTypesOptions = res.data.list;
                }
            });
        }


    $(document).ready(function () {
        //激活翻页按钮
        $('#tableId_previous').attr('class', 'paginate_button page-item previous')
        $('#tableId_next').attr('class', 'paginate_button page-item next')
        //隐藏原生搜索框
        $('#tableId_filter').hide()
        //设置右上角用户名
        $('.dropdown-menu h5').html(window.sessionStorage.getItem('username'))
        //设置项目名
        $('.sidebar-header h3 a').html(projectName)
        setMenu();
        init();

        if(window.sessionStorage.getItem('role') != '员工' && window.sessionStorage.getItem('role') != '部门主管' ){
            $(".clockIn").remove();
        }
        //查询级联表的搜索下拉框
        zhiwuTypesSelect();
        zhichengTypesSelect();
        sexTypesSelect();
        educationTypesSelect();
        roleTypesSelect();

        //查询当前表的搜索下拉框
        chuqinTypesSelect();
        getDataList();

        //级联表的下拉框赋值
                                                                                                 
                                                 
    zhiwuTypesSelectSearch();
                         
    zhichengTypesSelectSearch();
                         
                         
    sexTypesSelectSearch();
                                                                                                                                                                         
    educationTypesSelectSearch();
    roleTypesSelectSearch();
                        
        //当前表的下拉框赋值
                                                                         
            chuqinTypesSelectSearch();
                        
    <%@ include file="../../static/myInfo.js"%>
    });
</script>
</body>

</html>