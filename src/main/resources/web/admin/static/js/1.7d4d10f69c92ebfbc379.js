webpackJsonp([1],{"2Ajw":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("Row",[a("Col",{attrs:{span:19}},[a("Button",{attrs:{icon:"ios-plus-empty",type:"dashed",size:"small"},on:{click:t.addTag}},[t._v("添加分类")]),t._v(" "),t._l(t.comboxes,function(e,n){return a("Tag",{key:e.id,attrs:{type:"dot",closable:"",color:t.colors[n%4]},on:{"on-close":function(a){t.removeTag(e.id)}},nativeOn:{click:function(a){t.modifyTag(e)}}},[t._v(t._s(e.name))])})],2),t._v(" "),a("Col",{staticStyle:{position:"relative"},attrs:{span:5}},[a("Card",{class:(t.active,"hint-wrap")},[a("Icon",{staticClass:"hint-expend",attrs:{type:"navicon-round"},nativeOn:{click:function(e){t.active=!t.active}}}),t._v(" "),a("p",[t._v("1.点击 "),a("span",{staticClass:"wrong-txt"},[t._v("‘添加分类’")]),t._v(" ，新增一个分类；")]),t._v(" "),a("p",[t._v("2.点击 "),a("span",{staticClass:"wrong-txt"},[t._v("分类名称")]),t._v("，即可修改此分类；")]),t._v(" "),a("p",[t._v("2.点击分类右侧的 "),a("span",{staticClass:"wrong-txt"},[t._v("删除")]),t._v(" 按钮，即可删除该分类")])],1)],1)],1),t._v(" "),a("Modal",{staticClass:"modal",attrs:{width:"360"},model:{value:t.deleteClassify,callback:function(e){t.deleteClassify=e},expression:"deleteClassify"}},[a("p",{staticStyle:{color:"#f60","text-align":"center"},attrs:{slot:"header"},slot:"header"},[a("Icon",{attrs:{type:"ios-information-circle"}}),t._v(" "),a("span",[t._v("删除提醒")])],1),t._v(" "),a("div",{staticStyle:{"text-align":"center"}},[a("p",[t._v("删除分类会使得已经分配该分类的指标失去这个分类标签,")]),t._v(" "),a("p",[t._v("是否继续？")])]),t._v(" "),a("div",{attrs:{slot:"footer"},slot:"footer"},[a("Button",{attrs:{type:"error",size:"large",long:""},on:{click:t.deleteClass}},[t._v("删除")])],1)])],1)},staticRenderFns:[]};var i=a("VU/8")({data:function(){return{comboxes:[],colors:["blue","green","red","yellow"],name:"",id:"",active:!1,deleteClassify:!1,deleteId:""}},created:function(){this.getComboxList()},methods:{modifyTag:function(t){this.modify=!0,this.name=t.name,this.id=t.id,this.handleAdd()},addTag:function(){this.name="",this.id="",this.handleAdd()},getComboxList:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/group/finds.do",{}).then(function(e){t.comboxes=e})},removeTag:function(t){this.deleteClassify=!0,this.deleteId=t},deleteClass:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/group/delete.do",{id:this.deleteId}).then(function(e){t.$Message.success("删除成功"),t.deleteClassify=!1,t.getComboxList()})},addSubmit:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/group/save.do",{groupName:this.name}).then(function(e){t.$Message.success("恭喜你，新增分类成功!"),t.getComboxList()})},modifySubmit:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/group/update.do",{groupName:this.name,id:this.id}).then(function(e){t.$Message.success("恭喜你，分类修改成功!"),t.getComboxList()})},handleSubmit:function(){this.id?this.modifySubmit():this.addSubmit()},handleAdd:function(){var t=this;this.$Modal.confirm({render:function(e){return e("Input",{props:{value:t.name,autofocus:!0,placeholder:"请输入分类名称..."},on:{input:function(e){t.name=e}}})},onOk:function(){t.handleSubmit()},onCancel:function(){t.name=""}})}}},n,!1,function(t){a("zgxF")},"data-v-1c022ee7",null);e.default=i.exports},"4QDY":function(t,e){},"5b2w":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("Gu7T"),i=a.n(n),s=a("Xxa5"),r=a.n(s),o=a("Dd8w"),d=a.n(o),l=a("exGp"),c=a.n(l),u={data:function(){return{formValidate:{name:"",indexStatus:""},params:{pageNumber:1,pageSize:10}}},methods:{handleSubmit:function(t){var e,a=this;(this.$emit("initList",this.formValidate),this.formValidate.name||this.formValidate.indexStatus)?this.$refs[t].validate((e=c()(r.a.mark(function t(e){var n;return r.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:if(!e){t.next=8;break}return t.next=3,a.$ajaxPost(a.projectName+"/mgmt/index/finds.do",d()({},a.formValidate,a.params));case 3:n=t.sent,a.$emit("listData",n),a.$emit("queryResult",!0),t.next=9;break;case 8:a.$Message.error("搜索条件有误");case 9:case"end":return t.stop()}},t,a)})),function(t){return e.apply(this,arguments)})):this.$Message.warning("无搜索条件")},handleReset:function(t){this.$refs.formValidate.resetFields(),this.$emit("initList",this.formValidate)},selectChange:function(){this.$emit("initList",this.formValidate)},inputChange:function(){this.$emit("initList",this.formValidate)}}},m={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("Form",{ref:"formValidate",attrs:{model:t.formValidate,"label-width":60}},[a("Row",[a("Col",{attrs:{span:8}},[a("FormItem",{attrs:{label:"名称",prop:"name"}},[a("Input",{attrs:{placeholder:"请输入名称..."},on:{"on-blur":t.inputChange},model:{value:t.formValidate.name,callback:function(e){t.$set(t.formValidate,"name","string"==typeof e?e.trim():e)},expression:"formValidate.name"}})],1)],1),t._v(" "),a("Col",{attrs:{span:6}},[a("FormItem",{attrs:{label:"状态",prop:"indexStatus"}},[a("Select",{attrs:{placeholder:"请选择指标状态..."},on:{"on-change":t.selectChange},model:{value:t.formValidate.indexStatus,callback:function(e){t.$set(t.formValidate,"indexStatus",e)},expression:"formValidate.indexStatus"}},[a("Option",{attrs:{value:"1"}},[t._v("有效指标")]),t._v(" "),a("Option",{attrs:{value:"2"}},[t._v("无效指标")])],1)],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"right"},attrs:{span:10}},[a("FormItem",[a("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit("formValidate")}}},[t._v("确定")]),t._v(" "),a("Button",{staticStyle:{"margin-left":"10px"},attrs:{type:"ghost"},on:{click:t.handleReset}},[t._v("重置")])],1)],1)],1)],1)},staticRenderFns:[]};var f=a("VU/8")(u,m,!1,function(t){a("P4zr")},"data-v-4208fe01",null).exports,h=(a("lbHh"),{props:["parentName","isAdd","formValidate","modify"],data:function(){var t=this;return{selects:[],groupList:[],disabledIndexStatus:"",disabledParentId:-1,ruleValidate:{name:[{required:!0,message:"请输入标题...",trigger:"blur"}],description:[{required:!0,message:"请输入指标描述...",trigger:"blur"}],evaluationStandards:[{required:!0,message:"请输入评分标准...",trigger:"blur"}],unitsId:{required:!0,validator:function(e,a,n){t.formValidate.unitsId?n():n(new Error("请选择单位..."))},trigger:"change"},indexStatus:{required:!0,trigger:"change",message:"请选择状态..."}}}},methods:{getInstitutionList:function(){var t=this;return c()(r.a.mark(function e(){var a;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.$ajaxPost(t.projectName+"/mgmt/units/finds.do");case 2:a=e.sent,t.selects=a;case 4:case"end":return e.stop()}},e,t)}))()},getGroupList:function(){var t=this;return c()(r.a.mark(function e(){var a;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.$ajaxPost(t.projectName+"/mgmt/group/finds.do");case 2:a=e.sent,t.groupList=a;case 4:case"end":return e.stop()}},e,t)}))()},resetFormateData:function(){this.handleReset(),this.$emit("getList")},addSubmit:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/index/save.do",this.formValidate).then(function(e){t.$Message.success("恭喜你，新增成功!"),t.resetFormateData()})},modifySubmit:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/index/update.do",this.formValidate).then(function(e){t.$Message.success("恭喜你，修改成功!"),t.resetFormateData()})},handleSubmit:function(t){var e=this;this.$refs[t].validate(function(t){if(t)e.modify?e.modifySubmit():e.addSubmit();else{var a=e.modify?"修改":"新增";e.$Message.error(a+"失败，请刷新后重新尝试")}})},handleReset:function(t){this.$emit("cancelAdd"),this.$refs.formValidate.resetFields()}},watch:{isAdd:function(){this.isAdd&&(this.getInstitutionList(),this.getGroupList(),this.disabledIndexStatus=this.formValidate.indexStatus,this.disabledParentId=this.formValidate.parentId),this.isAdd&&this.modify&&(this.disabledIndexStatus="",this.disabledParentId=-1)}}}),p={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"add-modify-index"},[a("Modal",{attrs:{title:t.modify?"修改":"新增","mask-closable":!1,width:65,closable:!1},model:{value:t.isAdd,callback:function(e){t.isAdd=e},expression:"isAdd"}},[a("Form",{ref:"formValidate",attrs:{model:t.formValidate,rules:t.ruleValidate,"label-width":90}},[a("FormItem",{attrs:{label:"上级指标"}},[a("Input",{attrs:{placeholder:t.parentName?t.parentName:"无父级指标，本身为一级指标",disabled:""}})],1),t._v(" "),a("FormItem",{attrs:{label:"指标名称",prop:"name"}},[a("Input",{attrs:{placeholder:"请输入指标名称..."},model:{value:t.formValidate.name,callback:function(e){t.$set(t.formValidate,"name","string"==typeof e?e.trim():e)},expression:"formValidate.name"}})],1),t._v(" "),a("FormItem",{attrs:{label:"指标描述",prop:"description"}},[a("Input",{attrs:{type:"textarea",placeholder:"请输入指标描述..."},model:{value:t.formValidate.description,callback:function(e){t.$set(t.formValidate,"description","string"==typeof e?e.trim():e)},expression:"formValidate.description"}})],1),t._v(" "),a("FormItem",{attrs:{label:"评分标准",prop:"evaluationStandards"}},[a("Input",{attrs:{type:"textarea",placeholder:"请输入评分标准..."},model:{value:t.formValidate.evaluationStandards,callback:function(e){t.$set(t.formValidate,"evaluationStandards","string"==typeof e?e.trim():e)},expression:"formValidate.evaluationStandards"}})],1),t._v(" "),a("FormItem",{attrs:{label:"指标总分",prop:"grossScore"}},[a("InputNumber",{attrs:{min:1},model:{value:t.formValidate.grossScore,callback:function(e){t.$set(t.formValidate,"grossScore",e)},expression:"formValidate.grossScore"}})],1),t._v(" "),a("FormItem",{attrs:{label:"分类",prop:"groupId"}},[a("Select",{attrs:{placeholder:"请选择分类...",multiple:""},model:{value:t.formValidate.groupId,callback:function(e){t.$set(t.formValidate,"groupId",e)},expression:"formValidate.groupId"}},t._l(t.groupList,function(e){return a("Option",{key:e.id,attrs:{value:e.id}},[t._v(t._s(e.name))])}))],1),t._v(" "),a("FormItem",{attrs:{label:"计量单位",prop:"unitsId"}},[a("Select",{attrs:{placeholder:"请选择计量单位..."},model:{value:t.formValidate.unitsId,callback:function(e){t.$set(t.formValidate,"unitsId",e)},expression:"formValidate.unitsId"}},t._l(t.selects,function(e){return a("Option",{key:e.id,attrs:{value:e.id}},[t._v(t._s(e.unitsName))])}))],1),t._v(" "),a("FormItem",{attrs:{label:"状态",prop:"indexStatus"}},[a("Select",{attrs:{placeholder:"请选择状态...",disabled:0!==t.disabledParentId&&"2"===t.disabledIndexStatus},model:{value:t.formValidate.indexStatus,callback:function(e){t.$set(t.formValidate,"indexStatus",e)},expression:"formValidate.indexStatus"}},[a("Option",{attrs:{value:"1"}},[t._v("有效指标")]),t._v(" "),a("Option",{attrs:{value:"2"}},[t._v("无效指标")])],1)],1)],1),t._v(" "),a("div",{attrs:{slot:"footer"},slot:"footer"},[a("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit("formValidate")}}},[t._v(t._s(t.modify?"修改":"新增"))]),t._v(" "),a("Button",{staticStyle:{"margin-left":"10px"},attrs:{type:"ghost"},on:{click:t.handleReset}},[t._v("取消")])],1)],1)],1)},staticRenderFns:[]},v=a("VU/8")(h,p,!1,null,null,null).exports,g=(a("BTaQ"),a("M4fF")),x=a.n(g),b={components:{queryForm:f,addFrom:v},data:function(){var t=this;return{yearList:[],isSetData:!1,selectedObject:{},isAdd:!1,ismodify:!1,editList:{},list:[],loading:!1,setYears:[],columns:[{title:"层级",key:"nodeIds",width:120,render:function(t,e){return t("div",[t("span",1===e.row.nodeIds.split(",").length?"一级指标":2===e.row.nodeIds.split(",").length?"二级指标":"三级指标")])}},{title:"名称",key:"name"},{title:"父级",key:"parentId",render:function(t,e){return t("div",[t("span",+e.row.parentId?e.row.parentName:"无")])}},{title:"指标总分",key:"grossScore",width:100},{title:"分类",key:"indexGroups",render:function(t,e){return t("div",[t("span",e.row.indexGroups.map(function(t){return t.groupName}).join(","))])}},{title:"单位",key:"unitsName",width:80},{title:"状态",key:"indexStatus",width:120,render:function(t,e){return t("div",[t("span",{style:{color:1==+e.row.indexStatus?"#67c23a":"#f56c6c"}},1==+e.row.indexStatus?"有效指标":"无效指标")])}},{title:"操作",key:"action",align:"center",render:function(e,a){return e("div",[e("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.editRow(a)}}},"修改")])}}],params:{pageNumber:1,pageSize:10},total:0,dataTree:[],canClick:!1,nodeInfo:[],selectParentId:0,parentName:"",searchInfo:{},levelIndex:"",textTips:"没有符合条件的指标",isQuery:!1,selectIndexStatus:""}},created:function(){this.getParentListData()},methods:{clickTreeNode:function(t){var e=this;return c()(r.a.mark(function a(){var n,i;return r.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:if(console.log(t),e.canClick=!0,0!==t.length){a.next=4;break}return a.abrupt("return");case 4:return e.selectParentId=t[0].id,e.selectIndexStatus=t[0].indexStatus,e.parentName=t[0].name,e.nodeInfo=t,e.loading=!0,a.next=11,e.$ajaxPost(e.projectName+"/mgmt/index/finds.do",d()({parentId:e.selectParentId},e.params,e.searchInfo));case 11:n=a.sent,e.loading=!1,e.list=n.rows,e.total=n.total,t[0].nodeIds&&(i=t[0].nodeIds.split(",").length,e.levelIndex=1===i?"一":2===i?"二":"三",0===e.total&&(e.searchInfo.name&&e.searchInfo.indexStatus||(e.textTips=e.levelIndex+"级指标 - “"+e.parentName+"” 下没有符合的指标")));case 16:case"end":return a.stop()}},a,e)}))()},changeRadio:function(){this.getParentListData(),this.list=[],this.canClick=!1},selectMethod:function(t){var e;this.params.pageNumber in this.selectedObject||(this.selectedObject[this.params.pageNumber]=[]),(e=this.selectedObject[this.params.pageNumber]).splice.apply(e,[0,this.selectedObject[this.params.pageNumber].length].concat(i()(t)))},formatSelectedData:function(){var t=[];for(var e in this.selectedObject){var a=this.selectedObject[e].map(function(t){return t.id});t.push.apply(t,i()(a))}return t},csvRows:function(){this.formatSelectedData();window.open(this.projectName+"/mgmt/index/exportExcel.do?locker=6f0c077ee53d4aa68b9eae5b3e853bb8","_self")},addRow:function(){this.canClick?(this.isAdd=!0,this.ismodify=!1,this.editList={name:"",parentId:this.selectParentId,indexStatus:"2"===this.selectIndexStatus?"2":"",unitsId:"",evaluationStandards:"",description:"",grossScore:1,groupId:[]}):this.$Message.warning("请选择左侧指标，确定新增的指标所属层级！")},editRow:function(t){var e=this;return c()(r.a.mark(function a(){var n,i;return r.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:if(n=t.row,0!==(i=n.parentId)){a.next=5;break}return e.editHande(n),a.abrupt("return");case 5:return a.next=7,e.$ajaxPost(e.projectName+"/mgmt/index/find.do",{id:i});case 7:if("1"===a.sent.indexStatus){a.next=11;break}return e.$Message.warning("该指标的上级指标状态为无效，无法进行修改！"),a.abrupt("return");case 11:e.editHande(n);case 12:case"end":return a.stop()}},a,e)}))()},editHande:function(t){this.isAdd=!0,this.ismodify=!0,this.editList={id:t.id,name:t.name,parentId:t.parentId,indexStatus:t.indexStatus,unitsId:t.unitsId,evaluationStandards:t.evaluationStandards,description:t.description,grossScore:t.grossScore,groupId:t.indexGroups.map(function(t){return t.groupId})}},loadListData:function(t){this.clickTreeNode(this.nodeInfo),this.getParentListData(),this.params.pageNumber=1},searchResult:function(t){this.list=t.rows,this.total=t.total},SearchListData:function(t){this.searchInfo=t},checkedSelected:function(){var t=this;this.selectedObject[this.params.pageNumber]&&this.selectedObject[this.params.pageNumber].length&&this.selectedObject[this.params.pageNumber].forEach(function(e){var a=x.a.filter(t.list,x.a.matches({id:e.id}));a.length&&(a[0]._checked=!0)})},queryResult:function(t){this.isQuery=t},pageChange:function(t){var e=this;return c()(r.a.mark(function a(){var n;return r.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:if(!e.isQuery){a.next=8;break}return a.next=3,e.$ajaxPost(e.projectName+"/mgmt/index/finds.do",d()({},e.searchInfo,{pageNumber:t,pageSize:10}));case 3:n=a.sent,e.list=n.rows,e.total=n.total,a.next=10;break;case 8:e.params.pageNumber=t,e.loadListData();case 10:case"end":return a.stop()}},a,e)}))()},cancelAdd:function(){this.isAdd=!1},getParentListData:function(){var t=this;return c()(r.a.mark(function e(){var a,n,i,s,o,d,l,c,u;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return a=[],n={title:"全部指标",parentId:0,id:0,expand:!0},e.next=4,t.$ajaxPost(t.projectName+"/mgmt/index/findAllIndex.do");case 4:for(i=e.sent,(s=i).forEach(function(e){t.$set(e,"title",e.name),0!==t.nodeInfo.length&&e.id===t.nodeInfo[0].id&&(t.$set(e,"selected",!0),t.$set(e,"expend",!0))}),o=s.map(function(t){return t.nodeIds.split(",").length}).sort().reverse()[0],d=[],l=function(t){d.push(s.filter(function(e){return e.nodeIds.split(",").length===t}))},c=1;c<=o;c++)l(c);for(u=1;u<d.length;u++)t.handleParentData(d[u-1],d[u]);n.children=d[0],a.push(n),t.dataTree=a;case 15:case"end":return e.stop()}},e,t)}))()},handleParentData:function(t,e){var a="";return t.forEach(function(t){e.forEach(function(e){a=e.nodeIds.split(",").slice(0,e.nodeIds.split(",").length-1).join(","),t.nodeIds===a&&(t.children||(t.children=[]),t.children.push(e))})})}}},y={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"index-list-template"},[a("Row",{staticClass:"index-show-content"},[a("Col",{staticClass:"index-tree",attrs:{span:"4"}},[a("Tree",{attrs:{data:t.dataTree},on:{"on-select-change":t.clickTreeNode}})],1),t._v(" "),a("Col",{staticClass:"index-table",attrs:{span:"20"}},[a("query-form",{ref:"queryForm",on:{initList:t.SearchListData,listData:t.searchResult,queryResult:t.queryResult}}),t._v(" "),a("Table",{ref:"selection",attrs:{columns:t.columns,data:t.list,loading:t.loading,"no-data-text":t.textTips},on:{"on-selection-change":t.selectMethod}},[a("Button",{staticClass:"m-marlr",attrs:{slot:"header",type:"ghost"},on:{click:t.addRow},slot:"header"},[t._v("新增")]),t._v(" "),a("Button",{attrs:{slot:"header",type:"ghost"},on:{click:t.csvRows},slot:"header"},[t._v("导出")])],1),t._v(" "),a("Page",{staticClass:"page",attrs:{total:t.total,"show-total":""},on:{"on-change":t.pageChange}})],1)],1),t._v(" "),a("add-from",{attrs:{isAdd:t.isAdd,modify:t.ismodify,parentName:t.parentName,formValidate:t.editList},on:{cancelAdd:t.cancelAdd,getList:t.loadListData}})],1)},staticRenderFns:[]};var _=a("VU/8")(b,y,!1,function(t){a("X4/j")},null,null);e.default=_.exports},"6FLI":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("Xxa5"),i=a.n(n),s=a("exGp"),r=a.n(s),o={data:function(){return{num:0,comboxes:[{num:0,id:0,list:[]}],parentName:"",allList:[],parentList:[],typeColor:"",checkedId:null,modalShow:!1,formValidate:{departmentName:"",departmentStatus:1,parentId:0},ruleValidate:{departmentName:[{required:!0,message:"输入内容...",trigger:"blur"}]},modify:!1,checkedIdArr:[]}},created:function(){this.getDepartmentAllList()},methods:{getDepartmentAllList:function(){var t=this;return r()(i.a.mark(function e(){var a;return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.$ajaxPost(t.projectName+"/mgmt/department/findAll.do");case 2:a=e.sent,t.allList=a,t.allList.forEach(function(e,a,n){t.checkedIdArr.map(function(t){return t.tag.id}).includes(e.id)?(t.$set(e,"typeColor","success"),t.$set(e,"checked",!0)):(t.$set(e,"typeColor","1"===e.departmentStatus?"":"info"),t.$set(e,"checked",!1))}),t.comboxes[0].list=t.allList.filter(function(t){return 0===t.parentId});case 6:case"end":return e.stop()}},e,t)}))()},getChildrenList:function(t,e,a){var n=this;this.comboxes.splice(t+1,this.comboxes.length);var i=this.allList.filter(function(t){return t.parentId===e});i.forEach(function(t){n.$set(t,"typeColor","1"===t.departmentStatus?"":"info"),n.$set(t,"checked",!1)}),this.comboxes[t+1]={num:t+1,id:e,tag:a,list:i}},checkedTagList:function(t,e){var a={};if(this.$set(a,"line",t),this.$set(a,"tag",e),this.checkedIdArr.map(function(t){return t.line}).includes(t)){var n=this.checkedIdArr.filter(function(e){return e.line===t})[0];this.checkedIdArr.splice(this.checkedIdArr.indexOf(n),1,a)}else this.checkedIdArr.push(a)},clickTag:function(t,e){this.comboxes.filter(function(t){return t.num===e})[0].list.forEach(function(t){t.typeColor="1"===t.departmentStatus?"":"info",t.checked=!1}),this.$set(t,"checked",!0),this.$set(t,"typeColor","success"),this.checkedId=t.id,this.getChildrenList(e,t.id,t),this.checkedTagList(e,t)},addDepartment:function(t){this.formValidate.departmentName="",this.num=t.num,this.modalShow=!0,this.modify=!1,this.formValidate.parentId=t.id;var e=this.allList.filter(function(e){return e.id===t.id});this.parentName=0!==e.length?e[0].departmentName:"无"},modifyDepartment:function(t){this.num=t.num;var e=t.list.filter(function(t){return t.checked});if(0!==e.length){this.modalShow=!0,this.modify=!0,this.formValidate={departmentName:e[0].departmentName,departmentStatus:Number(e[0].departmentStatus),parentId:e[0].parentId,id:e[0].id};var a=this.allList.filter(function(e){return e.id===t.id});this.parentName=0!==a.length?a[0].departmentName:"无"}else this.$Message.error("请选择需要修改的部门")},handleSubmit:function(t){var e,a=this;this.$refs[t].validate((e=r()(i.a.mark(function t(e){return i.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:if(!e){t.next=11;break}return t.next=3,a.$ajaxPost(a.modify?a.projectName+"/mgmt/department/update.do":a.projectName+"/mgmt/department/save.do",a.formValidate);case 3:return t.sent,a.$Message.success(a.modify?"修改成功!":"新增成功!"),a.modalShow=!1,t.next=8,a.getDepartmentAllList();case 8:a.autoCheckedTag(),t.next=12;break;case 11:a.$Message.error("新增部门失败，请刷新后重新尝试");case 12:case"end":return t.stop()}},t,a)})),function(t){return e.apply(this,arguments)}))},autoCheckedTag:function(){var t=this;this.checkedIdArr.forEach(function(e){t.clickTag(e.tag,e.line)})},handleReset:function(t){this.$refs[t].resetFields(),this.modalShow=!1}}},d={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"department-manage"},[t._l(t.comboxes,function(e){return[a("el-card",{key:e.num,attrs:{shadow:"hover"}},[t._l(e.list,function(n){return a("el-tag",{key:n.id,attrs:{type:n.typeColor,checkable:!0},nativeOn:{click:function(a){t.clickTag(n,e.num)}}},[t._v("\n        "+t._s(n.departmentName)+t._s(n.checked)+"\n      ")])}),t._v(" "),a("el-button",{attrs:{icon:"el-icon-edit",size:"small"},on:{click:function(a){t.modifyDepartment(e)}}}),t._v(" "),a("el-button",{attrs:{icon:"el-icon-plus",size:"small"},on:{click:function(a){t.addDepartment(e)}}})],2)]}),t._v(" "),a("Modal",{attrs:{title:t.modify?"修改":"新增","mask-closable":!1,width:35,closable:!1},model:{value:t.modalShow,callback:function(e){t.modalShow=e},expression:"modalShow"}},[a("Form",{ref:"formValidate",attrs:{model:t.formValidate,rules:t.ruleValidate,"label-width":70}},[a("FormItem",{attrs:{label:"上级部门"}},[a("Input",{attrs:{placeholder:0===t.num?"无":t.parentName,disabled:""}})],1),t._v(" "),a("FormItem",{attrs:{label:"部门名称",prop:"departmentName"}},[a("Input",{attrs:{placeholder:"请输入部门名称..."},on:{"on-enter":function(e){t.handleSubmit("formValidate")}},model:{value:t.formValidate.departmentName,callback:function(e){t.$set(t.formValidate,"departmentName",e)},expression:"formValidate.departmentName"}})],1),t._v(" "),a("FormItem",{attrs:{label:"是否撤销",prop:"departmentStatus"}},[a("i-switch",{attrs:{size:"large","true-value":1,"false-value":2},model:{value:t.formValidate.departmentStatus,callback:function(e){t.$set(t.formValidate,"departmentStatus",e)},expression:"formValidate.departmentStatus"}},[a("span",{attrs:{slot:"open"},slot:"open"},[t._v("启用")]),t._v(" "),a("span",{attrs:{slot:"close"},slot:"close"},[t._v("撤销")])]),t._v(" "),a("span",{staticClass:"hint-txt"},[t._v("(注：撤销后对应部门下的指标均不可见，但不会被删除。)")])],1)],1),t._v(" "),a("div",{attrs:{slot:"footer"},slot:"footer"},[a("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit("formValidate")}}},[t._v(t._s(t.modify?"修改":"新增"))]),t._v(" "),a("Button",{staticStyle:{"margin-left":"8px"},attrs:{type:"ghost"},on:{click:function(e){t.handleReset("formValidate")}}},[t._v("取消")])],1)],1)],2)},staticRenderFns:[]};var l=a("VU/8")(o,d,!1,function(t){a("4QDY")},"data-v-34c55688",null);e.default=l.exports},IqvK:function(t,e){},NddZ:function(t,e){},P4zr:function(t,e){},Vq6z:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={data:function(){return{comboxes:[]}},created:function(){this.getComboxList()},methods:{getComboxList:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/city/cityCombox.do",{}).then(function(e){console.log(e),t.comboxes=e})}}},i={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]},s=a("VU/8")(n,i,!1,null,null,null);e.default=s.exports},"X4/j":function(t,e){},piJZ:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("Button",{attrs:{icon:"ios-plus-empty",type:"dashed",size:"small"},on:{click:t.addTag}},[t._v("添加单位")]),t._v(" "),t._l(t.comboxes,function(e,n){return a("Tag",{key:e.id,attrs:{type:"dot",closable:"",color:t.colors[n%4]},on:{"on-close":function(a){t.removeTag(e.id)}},nativeOn:{click:function(a){t.modifyTag(e)}}},[t._v(t._s(e.unitsName))])})],2)},staticRenderFns:[]};var i=a("VU/8")({data:function(){return{comboxes:[],colors:["blue","green","red","yellow"],unitsName:"",id:""}},created:function(){this.getComboxList()},methods:{modifyTag:function(t){this.modify=!0,this.unitsName=t.unitsName,this.id=t.id,this.handleAdd()},addTag:function(){this.unitsName="",this.id="",this.handleAdd()},getComboxList:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/units/finds.do",{}).then(function(e){t.comboxes=e})},removeTag:function(t){var e=this;this.$ajaxPost(this.projectName+"/mgmt/units/delete.do",{id:t}).then(function(t){e.$Message.success("删除成功"),e.getComboxList()})},addSubmit:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/units/save.do",{unitsName:this.unitsName}).then(function(e){t.$Message.success("恭喜你，新增单位成功!"),t.getComboxList()})},modifySubmit:function(){var t=this;this.$ajaxPost(this.projectName+"/mgmt/units/update.do",{unitsName:this.unitsName,id:this.id}).then(function(e){t.$Message.success("恭喜你，单位修改成功!"),t.getComboxList()})},handleSubmit:function(){this.id?this.modifySubmit():this.addSubmit()},handleAdd:function(){var t=this;this.$Modal.confirm({render:function(e){return e("Input",{props:{value:t.unitsName,autofocus:!0,placeholder:"请输入单位名称..."},on:{input:function(e){t.unitsName=e}}})},onOk:function(){t.handleSubmit()},onCancel:function(){t.unitsName=""}})}}},n,!1,function(t){a("IqvK")},null,null);e.default=i.exports},zBf4:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("Xxa5"),i=a.n(n),s=a("exGp"),r=a.n(s),o=a("PJh5"),d=a.n(o),l={data:function(){return{form:{id:null,startTime:"",endTime:"",status:"1"}}},created:function(){this.getSetOverTime()},methods:{getSetOverTime:function(){var t=this;return r()(i.a.mark(function e(){var a;return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t.time=[],e.next=3,t.$ajaxPost(t.projectName+"/mgmt/openTime/find.do");case 3:a=e.sent,t.form.startTime=a.startTime,t.form.endTime=a.endTime,t.statusFlag=a.status,t.form.id=a.id;case 8:case"end":return e.stop()}},e,t)}))()},changeTimeStart:function(t){this.form.startTime=d()(t).format("YYYY-MM-DD HH:mm:ss")},changeTimeEnd:function(t){this.form.endTime=d()(t).format("YYYY-MM-DD HH:mm:ss")},editorTime:function(){var t=this;return r()(i.a.mark(function e(){var a,n;return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t.form.startTime>t.form.endTime&&(a=t.form.startTime,t.form.startTime=t.form.endTime,t.form.endTime=a),e.next=3,t.$ajaxPost(t.projectName+"/mgmt/openTime/update.do",t.form);case 3:n=e.sent,t.$Message.success(n),t.getSetOverTime();case 6:case"end":return e.stop()}},e,t)}))()}}},c={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"time-manage"},[a("el-card",{staticClass:"box-card"},[a("el-form",{attrs:{model:t.form}},[a("el-form-item",{attrs:{label:"设置时间：","label-width":"120px"}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择开始时间"},on:{change:t.changeTimeStart},model:{value:t.form.startTime,callback:function(e){t.$set(t.form,"startTime",e)},expression:"form.startTime"}}),t._v("  至 \n        "),a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择结束时间"},on:{change:t.changeTimeEnd},model:{value:t.form.endTime,callback:function(e){t.$set(t.form,"endTime",e)},expression:"form.endTime"}})],1),t._v(" "),a("el-form-item",{attrs:{"label-width":"120px"}},[a("el-switch",{attrs:{"active-color":"#13ce66","inactive-color":"#ff4949","active-value":"1","inactive-value":"2","active-text":"启用","inactive-text":"禁用"},model:{value:t.form.status,callback:function(e){t.$set(t.form,"status",e)},expression:"form.status"}})],1)],1),t._v(" "),a("p",{staticClass:"editor-btn"},[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:t.editorTime}},[t._v("保存")])],1)],1),t._v(" "),a("el-card",{staticClass:"card-tips",attrs:{shadow:"always"}},[a("div",{staticClass:"tip-title"},[t._v("温馨提示")]),t._v("\n    时间管理用于管控指标数据的录入时间，若启用该配置，则在规定时间以外，任何人均无法录入和修改指标数据。\n  ")])],1)},staticRenderFns:[]};var u=a("VU/8")(l,c,!1,function(t){a("NddZ")},null,null);e.default=u.exports},zgxF:function(t,e){}});
//# sourceMappingURL=1.7d4d10f69c92ebfbc379.js.map