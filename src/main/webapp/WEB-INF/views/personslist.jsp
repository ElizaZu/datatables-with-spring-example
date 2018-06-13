<%--
  Created by IntelliJ IDEA.
  User: Eliza
  Date: 21.03.2018
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Datatables ajax example</title>

    <script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.1/css/buttons.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.1/css/responsive.bootstrap.min.css"/>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.5/jszip.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>

    <!--<script type="text/javascript" src="/static/datatables/Responsive-2.2.1/js/dataTables.responsive.min.js"></script>
    <script type="text/javascript" src="/static/datatables/Responsive-2.2.1/js/responsive.bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/datatables/Responsive-2.2.1/js/responsive.bootstrap.min.js"></script>

    <script type="text/javascript" src="/static/datatables/datatables-ru.js"></script>-->

    <style>
        .btn-group{
            margin: 5px;
        }
        .center{
            text-align: center;
        }
        th{
            text-align: center;
        }
        #container{
            padding: 15px;
        }
        .floatRight{
            float: right;
        }
    </style>
</head>

<body>
<div id="container">
    <div class="well lead">Datatables ajax example</div>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Persons</span></div>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                            <table class="table table-hover table-bordered table-list" id="tb-departments">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Lastname</th>
                                    <th>Firstname</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${persons}" var="person">
                                    <tr>
                                        <td>${person.id}</td>
                                        <td>${person.lastname}</td>
                                        <td>${person.firstname}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
        </div>
    </div>
    <div class="panel-footer"></div>
    </div>
</div>

</div>

<script>

    var table;
    $(document).ready(function () {

        table = $('#tb-departments').DataTable(
            {
                responsive: true,
                //stateSave: true,
                display: true,
                //language: tables_lang,
                pageLength: 25,
                autoWidth: false,
                lengthMenu: [[10, 25, 50, 75, 100, -1], [10, 25, 50, 75, 100, "Все"]],
                dom: "<'row'<'col-sm-12'B>><'row'<'col-sm-6'l><'col-sm-6'f>>rtip",
                buttons: [
                    { extend: 'copyHtml5', text: '<span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span>&nbsp;', titleAttr: 'Copy' },
                    { extend: 'excelHtml5', text: '<span class="glyphicon glyphicon-save" aria-hidden="true"></span>&nbsp;Excel', titleAttr: 'Save as Excel' },
                    { extend: 'pdfHtml5', text: '<span class="glyphicon glyphicon-save" aria-hidden="true"></span>&nbsp;Pdf', titleAttr: 'Save as PDF' }
                ]
            });
    });

</script>
</body>
</html>
