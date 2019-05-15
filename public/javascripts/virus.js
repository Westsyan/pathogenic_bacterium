var array = ["目（order）", "科（family）", "亚科（subfamily）", "属（genus）", "种（species）", "Genome Composition",
    "是否感染人", "是否感染动物", "是否感染植物", "传播途径", "感染剂量", "体外存活条件", "有效消毒剂",
    "预后/死亡率", "有效治疗药物", "疫苗", "中国（2006）", "美国NIH (2016)", "美国BMBL (2009)",
    "澳大利亚/新西兰 Australia/New Zealand  (2010)", "比利时 Belgium (2008)", "加拿大 Canada (2015)",
    "欧盟 EU (2000)", "德国 Germany (2013)", "日本 Japan", "新加坡 Singapore", "瑞士 Switzerland",
    "英国 UK (2013)", "Select Agent CDC", "Select Agent USDA"];

var tax =  ["目（order）", "科（family）", "亚科（subfamily）", "属（genus）", "种（species）"];

var taxValue = ["order", "family", "subfamily", "genus", "species"];

var taxHtml = "";

$.each(tax, function (n, value) {
    taxHtml += "<label style='margin-right: 15px'>" +
            "<input type='checkbox' checked='checked' value='" + taxValue[n] + "' onclick=\"setColumns('" + taxValue[n] + "')\">" + value +
            "</label>"
    }
);

$("#tax").append(taxHtml);


var posi = ["Genome Composition", "是否感染人", "是否感染动物", "是否感染植物", "传播途径", "感染剂量",
    "体外存活条件", "有效消毒剂", "预后/死亡率", "有效治疗药物", "疫苗"];

var posiValue = ["genome_composition", "isPeople", "isAnimal", "isPlant", "tranRoute", "infectiveDose",
    "survivalCondition", "isSanitizer", "deathRate", "isMedicine", "isVaccine"];

var posiHtml = "";

$.each(posi, function (n, value) {
    posiHtml += "<label style='margin-right: 15px'>" +
            "<input type='checkbox' checked='checked' value='" + posiValue[n] + "' onclick=\"setColumns('" + posiValue[n] + "')\">" + value +
            "</label>"
    }
);

$("#position").append(posiHtml);


var risk = ["中国（2006）", "美国NIH (2016)", "美国BMBL (2009)",
    "澳大利亚/新西兰 Australia/New Zealand  (2010)", "比利时 Belgium (2008)", "加拿大 Canada (2015)",
    "欧盟 EU (2000)", "德国 Germany (2013)", "日本 Japan", "新加坡 Singapore", "瑞士 Switzerland",
    "英国 UK (2013)", "Select Agent CDC", "Select Agent USDA"];

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



var values = ["order", "family", "subfamily", "genus", "species", "genome_composition", "isPeople", "isAnimal",
    "isPlant", "tranRoute", "infectiveDose", "survivalCondition", "isSanitizer", "deathRate",
    "isMedicine", "isVaccine", "chinaRisk", "usanihRisk", "usabmblRisk", "australiaNewzealandRisk",
    "belgiumRisk", "canadaRisk", "euRisk", "germanyRisk", "japanRisk", "singaporeRisk",
    "switzerlandRisk", "ukRisk", "selectAgentCdc", "selectAgentUsda"];
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
            title: "操作",
            formatter: function (value, row, index) {
                var de = "<a href='/english/admin/Virus/updateBefore?id=" + row.id + "' target='_blank'><button class='update' title='修改'><i class='fa fa-edit'></i></button></a>" +
                    "<button class='delete' onclick='openDelete(this)' value='" + row.name + "' id='" + row.id + "' title='删除'><i class='fa fa-trash'></i></button>";
                return de;
            }
        },{
            field:"name",
            title:"名称",
            formatter:function (value,row,index) {
                var de = "<i><a href='/english/Virus/moreInfo?id="+ row.id+"' target='_blank'>" + row.name +"</a></i>";
                return de;
            }
        }]
    });
}else{
    $("#table").bootstrapTable({
        columns: [{
            field:"name",
            title:"名称",
            formatter:function (value,row,index) {
                var de = "<i><a href='/english/Virus/moreInfo?id="+ row.id+"' target='_blank'>" + row.name +"</a></i>";
                return de;
            }
        }]
    });
}


var hiddenArray = ["order","family","subfamily","genus", "genome_composition", "isAnimal","isPlant", "infectiveDose", "survivalCondition", "deathRate",
     "isVaccine", "usabmblRisk", "australiaNewzealandRisk",
    "belgiumRisk", "canadaRisk", "euRisk", "germanyRisk", "japanRisk", "singaporeRisk",
    "switzerlandRisk", "ukRisk", "selectAgentCdc", "selectAgentUsda"];
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

function loadVirus() {
    var index = layer.load(1);
    $.ajax({
        url : "/english/Virus/getAllVirus",
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
            title: '删除?',
            text: "确认删除病毒 \"" + obj.value + "\" ?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '确认',
            cancelButtonText: "取消",
            closeOnConfirm: false
        }, function () {
            $.ajax({
                url: "/english/admin/Virus/deleteVirus?id=" + obj.id,
                type: "delete",
                success: function (data) {
                    if (data.valid == "false") {
                        swal("错误", data.message, "error")
                    } else {
                        swal({
                            type: "success",
                            title: "\n",
                            text: "删除成功！"
                        }, function () {
                            loadVirus();
                        })
                    }
                }
            })
        }
    )
}