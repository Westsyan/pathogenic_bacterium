
var tax =  ["Order", "Family","Genus", "Species"];

var taxValue = ["order", "family","genus", "species"];

var taxHtml = "";

$.each(tax, function (n, value) {
        taxHtml += "<label style='margin-right: 15px'>" +
            "<input type='checkbox' checked='checked' value='" + taxValue[n] + "' onclick=\"setColumns('" + taxValue[n] + "')\">" + value +
            "</label>"
    }
);

$("#tax").append(taxHtml);


var posi = ["Infected humans", "Infected animals", "Infected plants", "Route of transmission", "Infective dose",
    "Vitro survival condition", "Effective disinfectant", "Mortality rate", "Effective therapeutic agent", "Vaccine"];

var posiValue = [ "isPeople", "isAnimal", "isPlant", "tranRoute", "infectiveDose",
    "survivalCondition", "isSanitizer", "deathRate", "isMedicine", "isVaccine"];

var posiHtml = "";

$.each(posi, function (n, value) {
        posiHtml += "<label style='margin-right: 15px'>" +
            "<input type='checkbox' checked='checked' value='" + posiValue[n] + "' onclick=\"setColumns('" + posiValue[n] + "')\">" + value +
            "</label>"
    }
);

$("#position").append(posiHtml);


var risk = ["CHINA（2006）", "USA NIH (2016)", "USA BMBL (2009)",
    "Australia/New Zealand (2010)", "Belgium (2008)", "Canada (2015)",
    "EU (2000)", "Germany (2013)", "Japan", "Singapore", "Switzerland",
    "UK (2013)", "Select Agent CDC", "Select Agent USDA"];

var riskValue = ["chinaRisk", "usanihRisk", "usabmblRisk", "australiaNewzealandRisk",
    "belgiumRisk", "canadaRisk", "euRisk", "germanyRisk", "japanRisk", "singaporeRisk",
    "switzerlandRisk", "ukRisk", "selectAgentCdc", "selectAgentUsda"];

var riskHtml = "";

$.each(risk, function (n, value) {
        riskHtml += "<label style='margin-right: 15px'>" +
            "<input type='checkbox' checked='checked' value='" + riskValue[n] + "' onclick=\"setColumns('" + riskValue[n] + "')\">" + value +
            "</label>"
    }
);

$("#risk").append(riskHtml);


var array = ["Order","Family","Genus","Species",
    "是否感染人","是否感染动物","是否感染植物","传播途径","感染剂量","体外存活条件","有效消毒剂",
    "预后/死亡率","有效治疗药物","疫苗","CHINA（2006）","USA NIH (2016)","USA BMBL (2009)",
    "Australia/New Zealand  (2010)","Belgium (2008)","Canada (2015)",
    "EU (2000)","Germany (2013)","Japan","Singapore", "Switzerland",
    "UK (2013)","Select Agent CDC","Select Agent USDA"];
var values = ["order", "family" , "genus", "species" , "isPeople" , "isAnimal",
    "isPlant", "tranRoute" , "infectiveDose" , "survivalCondition", "isSanitizer", "deathRate",
    "isMedicine", "isVaccine", "chinaRisk" , "usanihRisk" , "usabmblRisk" , "australiaNewzealandRisk",
    "belgiumRisk" , "canadaRisk" , "euRisk", "germanyRisk" , "japanRisk", "singaporeRisk" ,
    "switzerlandRisk" , "ukRisk", "selectAgentCdc" , "selectAgentUsda"];
var html = "";
$.each(array, function (n, value) {
        html += "<label style='margin-right: 15px'>" +
            "<input type='checkbox' checked='checked' value='" + values[n] + "' onclick=\"setColumns('" + values[n] + "')\">" + value +
            "</label>"
    }
);
$("#checkbox").append(html);


var href = window.location.href;

if(String(href).indexOf("/admin/") != -1) {
    $("#table").bootstrapTable({
        columns: [{
            field: "operation",
            title: "Operation",
            formatter: function (value, row, index) {
                var de = "<a href='/english/admin/Fungus/updateBefore?id=" + row.id + "' target='_blank'><button class='update' title='修改'><i class='fa fa-edit'></i></button></a>" +
                    "<button class='delete' onclick='openDelete(this)' value=\"" + row.name + "\" id='" + row.id + "' title='删除'><i class='fa fa-trash'></i></button>";
                return de;
            }
        },{
            field:"name",
            title:"Name",
            formatter: function (value,row,index) {
                var de = "<i><a href='/english/Fungus/moreInfo?id=" + row.id +"' target='_blank'>" + row.name+"</a></i>";
                return de;
            }
        }]
    });
}else{
    $("#table").bootstrapTable({
        columns:[{
            field: "name",
            title: "Name",
            formatter: function (value, row, index) {
                var de = "<i><a href='/english/Fungus/moreInfo?id=" + row.id + "' target='_blank'>" + row.name + "</a></i>";
                return de;
            }
        }]
    });
}
var hiddenArray = ["order", "family" , "genus", "isAnimal", "isPlant", "infectiveDose" , "survivalCondition", "deathRate",
     "isVaccine","usabmblRisk" , "australiaNewzealandRisk",
    "belgiumRisk" , "canadaRisk" , "euRisk", "germanyRisk" , "japanRisk", "singaporeRisk" ,
    "switzerlandRisk" , "ukRisk", "selectAgentCdc" , "selectAgentUsda"];
$.each(hiddenArray, function (n, value) {
        $('#table').bootstrapTable('hideColumn', value);
        $("input:checkbox[value=" + value + "]").attr("checked", false)
    }
);


function setColumns(value) {
    var element = $("input:checkbox[value=" + value + "]");
    if (element.is(":checked")) {
        $('#table').bootstrapTable('showColumn', value);
    } else {
        $('#table').bootstrapTable('hideColumn', value);
    }
}

function loadFungus() {
    var index = layer.load(1);
    $.ajax({
        url : "/english/Fungus/getAllFungus",
        type: "get",
        dataType : "json",
        success : function (data) {
            layer.close(index);
            $("#table").bootstrapTable('load',data)
        }
    });
}


function openDelete(obj) {
    swal({
            title: 'Delete?',
            text: "Delete fungus \"" + obj.value + "\" ?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'OK',
            cancelButtonText: "Cancel",
            closeOnConfirm: false
        }, function () {
            $.ajax({
                url: "/english/admin/Fungus/deleteFungus?id=" + obj.id,
                type: "delete",
                success: function (data) {
                    if (data.valid == "false") {
                        swal("error", data.message, "error")
                    } else {
                        swal({
                            type: "success",
                            title: "\n",
                            text: "Delete Success！"
                        }, function () {
                            loadFungus();
                        })
                    }
                }
            })
        }
    )
}