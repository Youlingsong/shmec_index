webpackJsonp([2],{"+L4n":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a("Xxa5"),r=a.n(o),i=a("woOf"),n=a.n(i),s=a("exGp"),l=a.n(s),d=a("Gu7T"),c=a.n(d),u={props:["isAdd","modify","formValidate","options"],data:function(){var t=this;return{selectAllData:[],ruleValidate:{name:[{required:!0,message:"请输入2-20位由字母、汉字、数字组成的账户名",pattern:/^([a-zA-Z0-9]|[\u4E00-\u9FA5]){2,20}$/,trigger:"blur"}],account:[{required:!0,message:"请输入2-20位由字母、汉字、数字组成的账户名",pattern:/^([a-zA-Z0-9]|[\u4E00-\u9FA5]){2,20}$/,trigger:"blur"}],password:[{required:!0,validator:function(e,a,o){/^(\w){6,20}$/.test(t.formValidate.password)||t.modify?o():o(new Error("请输入由6-20个字母、数字、下划线组成的密码..."))},trigger:"blur"}]},secondCityList:[],dataTree:[]}},created:function(){this.getParentListData(),this.getCityLIstData()},methods:{getCityLIstData:function(){var t=this;return l()(r.a.mark(function e(){var a,o,i,n,s;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.$ajaxPost(t.projectName+"/mgmt/city/findAll.do");case 2:a=e.sent,i=(o=a).filter(function(t){return"0"===t.levelCode}),n=o.filter(function(t){return"1"===t.levelCode}),s=o.filter(function(t){return"2"===t.levelCode}),i.forEach(function(t,e){t.children||(t.children=[]),t.children.push(t)}),n.forEach(function(t,e){var a={cityName:t.cityName,cityStatus:t.cityStatus,id:t.id,levelCode:t.levelCode,parentId:t.parentId};t.children||(t.children=[]);var o=s.filter(function(e){return e.parentId===t.id});o.unshift(a),t.children=o}),t.secondCityList=[].concat(c()(i),c()(n));case 10:case"end":return e.stop()}},e,t)}))()},getParentListData:function(){var t=this;return l()(r.a.mark(function e(){var a,o,i,n,s,l,d;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return[],e.next=3,t.$ajaxPost(t.projectName+"/mgmt/index/findAllIndex.do");case 3:for(a=e.sent,(o=a).forEach(function(e){t.$set(e,"label",e.name)}),i=o.map(function(t){return t.nodeIds.split(",").length}).sort().reverse()[0],n=[],s=function(t){n.push(o.filter(function(e){return e.nodeIds.split(",").length===t}))},l=1;l<=i;l++)s(l);for(d=1;d<n.length;d++)t.handleParentData(n[d-1],n[d]);t.dataTree=n[0];case 12:case"end":return e.stop()}},e,t)}))()},handleParentData:function(t,e){var a="";return t.forEach(function(t){e.forEach(function(e){a=e.nodeIds.split(",").slice(0,e.nodeIds.split(",").length-1).join(","),t.nodeIds===a&&(t.children||(t.children=[]),t.children.push(e))})})},handleSubmit:function(t){var e,a=this;this.$refs[t].validate((e=l()(r.a.mark(function t(e){var o;return r.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:if(!e){t.next=21;break}if(!a.formValidate.phone){t.next=6;break}if(/1[3456789][0-9]{9}$/.test(a.formValidate.phone)){t.next=6;break}return a.$Message.warning("电话号码有误..."),t.abrupt("return");case 6:if(!a.formValidate.email){t.next=11;break}if(/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(a.formValidate.email)){t.next=11;break}return a.$Message.warning("电子邮箱有误..."),t.abrupt("return");case 11:return a.formValidate.cityIds=a.options.cityIds1,a.formValidate.allowReadIndexs=a.$refs.tree.getCheckedKeys(),t.next=15,a.$ajaxPost(a.modify?a.projectName+"/mgmt/account/updateInfo.do":a.projectName+"/mgmt/account/save.do",a.formValidate);case 15:o=t.sent,a.$Message.success(o),a.$emit("submitAdd"),a.handleReset(),t.next=22;break;case 21:a.$Message.error("填写有误，请检查后进行下一步操作!");case 22:case"end":return t.stop()}},t,a)})),function(t){return e.apply(this,arguments)}))},handleReset:function(){this.$emit("cancelAdd"),this.$refs.formValidate.resetFields()},callback:function(){this.$emit("callback")}},watch:{isAdd:function(){this.isAdd&&this.$refs.tree.setCheckedKeys([])}}},m={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"user-add-template"},[a("Modal",{attrs:{"class-name":"user-add-modal",title:t.modify?"修改用户":"新增用户","mask-closable":!1,width:70,closable:!1},model:{value:t.isAdd,callback:function(e){t.isAdd=e},expression:"isAdd"}},[a("h3",{staticStyle:{"text-align":"center","font-size":"18px","margin-bottom":"10px"}},[t._v("基本信息")]),t._v(" "),a("Form",{ref:"formValidate",attrs:{model:t.formValidate,rules:t.ruleValidate,"label-width":110}},[a("FormItem",{attrs:{label:"用户名",prop:"name"}},[a("Input",{attrs:{placeholder:"请输入用户名..."},model:{value:t.formValidate.name,callback:function(e){t.$set(t.formValidate,"name","string"==typeof e?e.trim():e)},expression:"formValidate.name"}})],1),t._v(" "),a("FormItem",{attrs:{label:"用户账号",prop:"account"}},[a("Input",{attrs:{placeholder:"请输入用户账号..."},model:{value:t.formValidate.account,callback:function(e){t.$set(t.formValidate,"account","string"==typeof e?e.trim():e)},expression:"formValidate.account"}})],1),t._v(" "),t.modify?t._e():a("FormItem",{attrs:{label:"密码",prop:"password"}},[a("Input",{attrs:{type:"password",placeholder:"请输入密码..."},model:{value:t.formValidate.password,callback:function(e){t.$set(t.formValidate,"password","string"==typeof e?e.trim():e)},expression:"formValidate.password"}})],1),t._v(" "),a("FormItem",{attrs:{label:"单位( 选填 )",prop:"institution"}},[a("Input",{attrs:{placeholder:"请输入单位...",clearable:""},model:{value:t.formValidate.institution,callback:function(e){t.$set(t.formValidate,"institution","string"==typeof e?e.trim():e)},expression:"formValidate.institution"}})],1),t._v(" "),a("FormItem",{attrs:{label:"工作( 选填 )",prop:"job"}},[a("Input",{attrs:{placeholder:"请输入工作...",clearable:""},model:{value:t.formValidate.job,callback:function(e){t.$set(t.formValidate,"job","string"==typeof e?e.trim():e)},expression:"formValidate.job"}})],1),t._v(" "),a("FormItem",{attrs:{label:"电话( 选填 )",prop:"phone"}},[a("Input",{attrs:{placeholder:"请输入电话...",clearable:""},model:{value:t.formValidate.phone,callback:function(e){t.$set(t.formValidate,"phone","string"==typeof e?e.trim():e)},expression:"formValidate.phone"}})],1),t._v(" "),a("FormItem",{attrs:{label:"电子邮箱( 选填 )",prop:"email"}},[a("Input",{attrs:{placeholder:"请输入电子邮箱...",clearable:""},model:{value:t.formValidate.email,callback:function(e){t.$set(t.formValidate,"email","string"==typeof e?e.trim():e)},expression:"formValidate.email"}})],1),t._v(" "),a("FormItem",{attrs:{label:"用户状态",prop:"accountStatus"}},[a("i-switch",{attrs:{size:"large","true-value":1,"false-value":2},model:{value:t.formValidate.accountStatus,callback:function(e){t.$set(t.formValidate,"accountStatus",e)},expression:"formValidate.accountStatus"}},[a("span",{attrs:{slot:"open"},slot:"open"},[t._v("启用")]),t._v(" "),a("span",{attrs:{slot:"close"},slot:"close"},[t._v("禁用")])])],1),t._v(" "),a("h3",{staticStyle:{"text-align":"center","font-size":"18px","margin-bottom":"10px"}},[t._v("指标信息")]),t._v(" "),a("FormItem",{attrs:{label:"选择可查看区域",prop:"cityIds"}},[a("Select",{attrs:{multiple:"",placeholder:"请选择可查看的区域"},model:{value:t.options.cityIds1,callback:function(e){t.$set(t.options,"cityIds1",e)},expression:"options.cityIds1"}},t._l(t.secondCityList,function(e){return a("OptionGroup",{key:e.id,attrs:{label:e.cityName}},t._l(e.children,function(e){return a("Option",{key:e.id+20,attrs:{value:e.id}},[t._v(t._s(e.cityName))])}))}))],1),t._v(" "),a("FormItem",{attrs:{label:"选择可查看指标"}},[a("el-tree",{ref:"tree",attrs:{data:t.dataTree,"show-checkbox":"","node-key":"id","check-strictly":!0,accordion:!0,"default-checked-keys":t.options.allowReadIndexs1}})],1)],1),t._v(" "),a("div",{attrs:{slot:"footer"},slot:"footer"},[a("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit("formValidate")}}},[t._v(t._s(t.modify?"修改":"新增"))]),t._v(" "),a("Button",{staticStyle:{"margin-left":"8px"},attrs:{type:"ghost"},on:{click:t.handleReset}},[t._v("取消")])],1)],1)],1)},staticRenderFns:[]};var p={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("Form",{ref:"formValidate",attrs:{model:t.formValidate,"label-width":80}},[a("Row",[a("Col",{attrs:{span:7}},[a("FormItem",{attrs:{label:"用户名",prop:"name"}},[a("Input",{attrs:{placeholder:"请输入用户名..."},model:{value:t.formValidate.name,callback:function(e){t.$set(t.formValidate,"name",e)},expression:"formValidate.name"}})],1)],1),t._v(" "),a("Col",{attrs:{span:6}},[a("FormItem",{attrs:{label:"用户状态",prop:"accountStatus"}},[a("Select",{attrs:{placeholder:"请选择用户状态...",filterable:"",clearable:!0},model:{value:t.formValidate.accountStatus,callback:function(e){t.$set(t.formValidate,"accountStatus",e)},expression:"formValidate.accountStatus"}},t._l(t.options,function(e){return a("Option",{key:e.value,attrs:{value:e.value}},[t._v(t._s(e.text))])}))],1)],1),t._v(" "),a("Col",{attrs:{span:6}},[a("FormItem",{staticClass:"m-txt-right"},[a("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit("formValidate")}}},[t._v("确定")]),t._v(" "),a("Button",{staticStyle:{"margin-left":"8px"},attrs:{type:"ghost"},on:{click:t.handleReset}},[t._v("重置")])],1)],1)],1)],1)},staticRenderFns:[]};var f={components:{userAdd:a("VU/8")(u,m,!1,function(t){a("G/bq")},null,null).exports,queryForm:a("VU/8")({data:function(){return{formValidate:{name:"",accountStatus:""},options:[{text:"启用",value:1},{text:"禁用",value:2}]}},methods:{handleSubmit:function(t){var e=this;this.$refs[t].validate(function(t){t&&e.$emit("submitAdd",e.formValidate)})},handleReset:function(t){this.$refs.formValidate.resetFields(),this.$emit("submitAdd",this.formValidate)}}},p,!1,function(t){a("blrR")},"data-v-2866122a",null).exports},data:function(){var t=this;return{list:[],loading:!1,editList:{name:"",account:"",accountStatus:1,phone:"",institution:"",job:"",password:"",email:"",allowReadIndexs:[],cityIds:[]},isAdd:!1,ismodify:!1,total:0,pageInfo:{pageNumber:1,pageSize:10},columns:[{title:"用户名",key:"name",align:"center"},{title:"用户账号",key:"account",align:"center"},{title:"用户状态",key:"accountStatus",align:"center",render:function(t,e){return t("div",[t("span",1==+e.row.accountStatus?"启用":"禁用")])}},{title:"电话",key:"phone",align:"center"},{title:"邮箱",key:"email",align:"center"},{title:"操作",key:"action",align:"center",render:function(e,a){var o=e("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.editRow(a)}}},"修改");e("Button",{props:{type:"ghost",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.$router.push("/info")}}},"移步个人中心");return e("div",[o,e("Button",{props:{type:"error",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.removeRow(a)}}},"删除"),e("Tooltip",{props:{content:"初始化密码后，密码为123456",theme:"light",placement:"top-end"}},[e("Button",{props:{size:"small"},style:{marginRight:"5px"},on:{click:function(){t.resetPasswordRow(a)}}},"初始化密码")])])}}],options:{allowReadIndexs1:[],cityIds1:[]}}},created:function(){this.loadListData()},methods:{loadListData:function(t){var e=this;return l()(r.a.mark(function a(){var o;return r.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return e.params=t?n()({},e.params,t):e.params,e.loading=!0,a.next=4,e.$ajaxPost(e.projectName+"/mgmt/account/finds.do",n()({},e.params,e.pageInfo));case 4:o=a.sent,e.loading=!1,e.list=o.rows,e.total=o.total;case 8:case"end":return a.stop()}},a,e)}))()},addRow:function(){this.isAdd=!0,this.ismodify=!1,this.editList={name:"",account:"",accountStatus:1,phone:"",institution:"",job:"",password:"",email:"",allowReadIndexs:[],cityIds:[]},this.options={allowReadIndexs1:[],cityIds1:[]}},editRow:function(t){var e=t.row;this.isAdd=!0,this.ismodify=!0,this.editList={id:e.id,name:e.name,account:e.account,institution:e.institution,job:e.job,accountStatus:Number(e.accountStatus),phone:e.phone,email:e.email,allowReadIndexs:[],cityIds:[]},this.options.allowReadIndexs1=0!==e.allowReadIndexs.length?e.allowReadIndexs.map(function(t){return t.id}):[],this.options.cityIds1=0!==e.citys.length?e.citys.map(function(t){return t.cityId}):[]},removeRow:function(t){var e=this;this.$Modal.confirm({title:"提示",content:"<p>确定删除该用户吗？</p>",loading:!0,onOk:function(){e.$ajaxPost(e.projectName+"/mgmt/account/delete.do",{id:t.row.id}).then(function(t){e.$Modal.remove(),e.$Message.success("删除成功"),e.loadListData()})}})},resetPasswordRow:function(t){var e=this;this.$Modal.confirm({title:"提示",content:"<p>确定初始化该用户的密码吗？</p>",loading:!0,onOk:function(){e.$ajaxPost(e.projectName+"/mgmt/account/initPassword.do",{id:t.row.id}).then(function(t){e.$Modal.remove(),e.$Message.success(t),e.loadListData()})}})},pageChange:function(t){this.pageInfo.pageNumber=t,this.loadListData()},cancelAdd:function(){this.isAdd=!1,this.options={allowReadIndexs1:[],cityIds1:[]},this.editList={name:"",account:"",accountStatus:1,phone:"",institution:"",job:"",password:"",email:"",allowReadIndexs:[],cityIds:[]}},callback:function(){this.isAdd=!0}}},h={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("query-form",{on:{submitAdd:t.loadListData}}),t._v(" "),a("user-add",{attrs:{isAdd:t.isAdd,formValidate:t.editList,modify:t.ismodify,options:t.options},on:{cancelAdd:t.cancelAdd,callback:t.callback,submitAdd:t.loadListData}}),t._v(" "),a("Table",{attrs:{columns:t.columns,data:t.list,loading:t.loading}},[a("Button",{staticClass:"m-marlr",attrs:{slot:"header",type:"ghost"},on:{click:t.addRow},slot:"header"},[t._v("新增")])],1),t._v(" "),a("Page",{staticClass:"page",attrs:{total:t.total,"show-total":""},on:{"on-change":t.pageChange}})],1)},staticRenderFns:[]};var v=a("VU/8")(f,h,!1,function(t){a("Yq/3")},null,null);e.default=v.exports},"9Yxw":function(t,e){},FQK4:function(t,e){},"G/bq":function(t,e){},LsrL:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a("Dd8w"),r=a.n(o),i=a("lbHh"),n=a.n(i),s={data:function(){var t=this;return{userForm:{id:0,name:"",phone:"",email:""},save_loading:!1,savePassLoading:!1,editPasswordModal:!1,oldPassError:"",inforValidate:{name:[{required:!0,message:"请输入2-20位由字母、汉字、数字、“_”、“.”的用户名",pattern:/^([a-zA-Z0-9]|[\u4E00-\u9FA5]|[._]){2,20}$/,trigger:"blur"}]},editPasswordForm:{password:"",newPassword:"",rePassword:""},passwordValidate:{password:[{required:!0,validator:function(t,e,a){""===e?a(new Error("原密码不可为空...")):a()},trigger:"blur"}],newPassword:[{required:!0,message:"请输入由6-20个字母、数字、下划线组成的密码...",pattern:/^(\w){6,20}$/,trigger:"blur"}],rePassword:[{required:!0,message:"请再次输入新密码",trigger:"blur"},{validator:function(e,a,o){a!==t.editPasswordForm.newPassword?o(new Error("两次输入密码不一致")):o()},trigger:"blur"}]},inputCodeVisible:!1,initPhone:"",gettingIdentifyCodeBtnContent:"获取验证码"}},methods:{cancelEditUserInfor:function(){this.$router.push("/adminIndex/index")},saveEdit:function(){var t=this;this.$refs.userForm.validate(function(e){if(e){if(t.userForm.phone)if(!/1[3456789][0-9]{9}$/.test(t.userForm.phone))return void t.$Message.warning("电话号码有误...");if(t.userForm.email)if(!/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(t.userForm.email))return void t.$Message.warning("电子邮箱有误...");t.saveInfoAjax()}})},showEditPassword:function(){this.editPasswordModal=!0},cancelEditPass:function(){this.editPasswordModal=!1,this.$refs.editPasswordForm.resetFields(),this.savePassLoading=!1},saveEditPass:function(){var t=this;this.$refs.editPasswordForm.validate(function(e){if(e){t.savePassLoading=!0;var a=JSON.parse(n.a.get("Temp-User")).name;t.$ajaxPost(t.projectName+"/staff/updatePassword.do",r()({},t.editPasswordForm,{name:a})).then(function(e){t.$Message.success("密码修改成功!"),t.savePassLoading=!1,t.editPasswordModal=!1,t.loginOutToLoginIn()})}})},init:function(){var t=this,e=n.a.get("U-ID");this.$ajaxPost(this.projectName+"/staff/find.do",{id:e}).then(function(e){var a={};["id","name","phone","email"].forEach(function(t){a[t]=e[t]}),t.userForm=a})},saveInfoAjax:function(){var t=this;this.save_loading=!0,this.$ajaxPost(this.projectName+"/staff/updateMyselfInfo.do",this.userForm).then(function(e){t.save_loading=!1,t.$Message.success(e),t.loginOutToLoginIn()})},loginOutToLoginIn:function(){var t=this;this.$ajaxPost(this.projectName+"/staff/logout.do").then(function(e){n.a.remove("Temp-User"),n.a.remove("administratorFlag"),n.a.remove("U-ID"),t.$router.replace("/login")})}},mounted:function(){this.init()}},l={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("Card",[a("p",{attrs:{slot:"title"},slot:"title"},[a("Icon",{attrs:{type:"person"}}),t._v("\n            个人信息\n        ")],1),t._v(" "),a("div",[a("Form",{ref:"userForm",attrs:{model:t.userForm,"label-width":100,"label-position":"right",rules:t.inforValidate}},[a("FormItem",{attrs:{label:"用户名：",prop:"name"}},[a("div",{staticStyle:{display:"inline-block",width:"300px"}},[a("Input",{model:{value:t.userForm.name,callback:function(e){t.$set(t.userForm,"name",e)},expression:"userForm.name"}})],1)]),t._v(" "),a("FormItem",{attrs:{label:"电话：",prop:"phone"}},[a("div",{staticStyle:{display:"inline-block",width:"300px"}},[a("Input",{model:{value:t.userForm.phone,callback:function(e){t.$set(t.userForm,"phone",e)},expression:"userForm.phone"}})],1)]),t._v(" "),a("FormItem",{attrs:{label:"电子邮箱：",prop:"email"}},[a("div",{staticStyle:{display:"inline-block",width:"300px"}},[a("Input",{model:{value:t.userForm.email,callback:function(e){t.$set(t.userForm,"email",e)},expression:"userForm.email"}})],1)]),t._v(" "),a("FormItem",{attrs:{label:"登录密码："}},[a("Button",{attrs:{type:"text",size:"small"},on:{click:t.showEditPassword}},[t._v("修改密码")])],1),t._v(" "),a("div",[a("Button",{staticStyle:{width:"100px"},attrs:{type:"text"},on:{click:t.cancelEditUserInfor}},[t._v("取消")]),t._v(" "),a("Button",{staticStyle:{width:"100px"},attrs:{type:"primary",loading:t.save_loading},on:{click:t.saveEdit}},[t._v("保存")])],1)],1)],1)]),t._v(" "),a("Modal",{attrs:{closable:!1,"mask-closable":!1,width:500},model:{value:t.editPasswordModal,callback:function(e){t.editPasswordModal=e},expression:"editPasswordModal"}},[a("h3",{staticStyle:{color:"#2D8CF0"},attrs:{slot:"header"},slot:"header"},[t._v("修改密码")]),t._v(" "),a("Form",{ref:"editPasswordForm",attrs:{model:t.editPasswordForm,"label-width":100,"label-position":"right",rules:t.passwordValidate}},[a("FormItem",{attrs:{label:"原密码",prop:"password"}},[a("Input",{attrs:{type:"password",placeholder:"请输入现在使用的密码"},model:{value:t.editPasswordForm.password,callback:function(e){t.$set(t.editPasswordForm,"password",e)},expression:"editPasswordForm.password"}})],1),t._v(" "),a("FormItem",{attrs:{label:"新密码",prop:"newPassword"}},[a("Input",{attrs:{type:"password",placeholder:"请输入新密码，至少6位字符"},model:{value:t.editPasswordForm.newPassword,callback:function(e){t.$set(t.editPasswordForm,"newPassword",e)},expression:"editPasswordForm.newPassword"}})],1),t._v(" "),a("FormItem",{attrs:{label:"确认新密码",prop:"rePassword"}},[a("Input",{attrs:{type:"password",placeholder:"请再次输入新密码"},model:{value:t.editPasswordForm.rePassword,callback:function(e){t.$set(t.editPasswordForm,"rePassword",e)},expression:"editPasswordForm.rePassword"}})],1)],1),t._v(" "),a("div",{attrs:{slot:"footer"},slot:"footer"},[a("Button",{attrs:{type:"text"},on:{click:t.cancelEditPass}},[t._v("取消")]),t._v(" "),a("Button",{attrs:{type:"primary"},on:{click:t.saveEditPass}},[t._v("保存")])],1)],1)],1)},staticRenderFns:[]};var d=a("VU/8")(s,l,!1,function(t){a("lKP7")},null,null);e.default=d.exports},"Yq/3":function(t,e){},blrR:function(t,e){},lKP7:function(t,e){},md3T:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a("woOf"),r=a.n(o),i=a("Xxa5"),n=a.n(i),s=a("exGp"),l=a.n(s),d=a("Gu7T"),c=a.n(d),u=a("lbHh"),m=a.n(u),p={props:["isAdd","formValidate","modify"],data:function(){var t=this;return{secondCityList:[],roleList:[],ruleValidate:{name:[{required:!0,message:"请输入5-20位由字母、汉字、数字组成的账户名",pattern:/^([a-zA-Z0-9]|[\u4E00-\u9FA5]){5,20}$/,trigger:"blur"}],password:[{required:!0,validator:function(e,a,o){/^(\w){6,20}$/.test(t.formValidate.password)||t.modify?o():o(new Error("请输入由6-20个字母、数字、下划线组成的密码..."))},trigger:"blur"}],cityId:[{required:!0,validator:function(t,e,a){e?a():a(new Error("请选择管理区域..."))},trigger:"change"}],roleId:[{required:!0,validator:function(t,e,a){e?a():a(new Error("请选择角色..."))},trigger:"change"}]},optionGroupShow:!0}},created:function(){this.getRoleData()},mounted:function(){this.getCityLIstData()},methods:{getCityLIstData:function(){var t=this;return l()(n.a.mark(function e(){var a,o,r,i,s;return n.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.$ajaxPost(t.projectName+"/mgmt/city/findAll.do");case 2:a=e.sent,r=(o=a).filter(function(t){return"0"===t.levelCode}),i=o.filter(function(t){return"1"===t.levelCode}),s=o.filter(function(t){return"2"===t.levelCode}),r.forEach(function(t,e){t.children||(t.children=[]),t.children.push(t)}),i.forEach(function(t,e){var a={cityName:t.cityName,cityStatus:t.cityStatus,id:t.id,levelCode:t.levelCode,parentId:t.parentId};t.children||(t.children=[]);var o=s.filter(function(e){return e.parentId===t.id});o.unshift(a),t.children=o}),t.secondCityList=[].concat(c()(r),c()(i)),0===t.secondCityList.length&&(t.optionGroupShow=!1,t.secondCityList=s);case 11:case"end":return e.stop()}},e,t)}))()},getRoleData:function(){var t=this;return l()(n.a.mark(function e(){var a;return n.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.$ajaxPost(t.projectName+"/staff/findsStaffRole.do");case 2:a=e.sent,t.roleList=a;case 4:case"end":return e.stop()}},e,t)}))()},cancelChooseIndex:function(){this.$emit("callback")},selectRole:function(t){var e=this;this.optionGroupShow&&1===t?(this.formValidate.cityId="",this.secondCityList[0].children.forEach(function(t){"1"===t.levelCode&&e.$set(t,"disabled",!0)})):this.secondCityList[0].children.forEach(function(t){"1"===t.levelCode&&e.$set(t,"disabled",!1)})},handleSubmit:function(t){var e,a=this;this.$refs[t].validate((e=l()(n.a.mark(function t(e){var o;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:if(!e){t.next=19;break}if(!a.formValidate.phone){t.next=6;break}if(/1[3456789][0-9]{9}$/.test(a.formValidate.phone)){t.next=6;break}return a.$Message.warning("电话号码有误..."),t.abrupt("return");case 6:if(!a.formValidate.email){t.next=11;break}if(/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(a.formValidate.email)){t.next=11;break}return a.$Message.warning("电子邮箱有误..."),t.abrupt("return");case 11:return t.next=13,a.$ajaxPost(a.modify?a.projectName+"/staff/updateInfo.do":a.projectName+"/staff/save.do",a.formValidate);case 13:o=t.sent,a.$Message.success(o),a.$emit("submitAdd"),a.$emit("cancelAdd"),t.next=20;break;case 19:a.$Message.error("填写有误，请检查后进行下一步操作!");case 20:case"end":return t.stop()}},t,a)})),function(t){return e.apply(this,arguments)}))},handleReset:function(t){this.$emit("cancelAdd"),this.$refs.formValidate.resetFields()},callback:function(){this.$emit("callback")}}},f={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("Modal",{attrs:{title:t.modify?"修改账户":"新增账户","mask-closable":!1,width:60,closable:!1},model:{value:t.isAdd,callback:function(e){t.isAdd=e},expression:"isAdd"}},[a("Form",{ref:"formValidate",attrs:{model:t.formValidate,rules:t.ruleValidate,"label-width":120}},[a("FormItem",{attrs:{label:"账户名",prop:"name"}},[a("Input",{attrs:{placeholder:"请输入账户名..."},model:{value:t.formValidate.name,callback:function(e){t.$set(t.formValidate,"name","string"==typeof e?e.trim():e)},expression:"formValidate.name"}})],1),t._v(" "),t.modify?t._e():a("FormItem",{attrs:{label:"密码",prop:"password"}},[a("Input",{attrs:{type:"password",placeholder:"请输入密码..."},model:{value:t.formValidate.password,callback:function(e){t.$set(t.formValidate,"password","string"==typeof e?e.trim():e)},expression:"formValidate.password"}})],1),t._v(" "),a("FormItem",{attrs:{label:"角色",prop:"roleId"}},[a("Select",{on:{"on-change":t.selectRole},model:{value:t.formValidate.roleId,callback:function(e){t.$set(t.formValidate,"roleId",e)},expression:"formValidate.roleId"}},t._l(t.roleList,function(e){return a("Option",{key:e.roleId,attrs:{value:e.roleId,disabled:!t.optionGroupShow&&1===e.roleId}},[t._v(t._s(e.roleName))])}))],1),t._v(" "),a("FormItem",{attrs:{label:"区域",prop:"cityId"}},[a("Select",{model:{value:t.formValidate.cityId,callback:function(e){t.$set(t.formValidate,"cityId",e)},expression:"formValidate.cityId"}},t._l(t.secondCityList,function(e){return t.optionGroupShow?a("OptionGroup",{key:e.id,attrs:{label:e.cityName}},t._l(e.children,function(e){return a("Option",{key:e.id+20,attrs:{value:e.id,disabled:e.disabled}},[t._v(t._s(e.cityName))])})):t._l(t.secondCityList,function(e){return a("Option",{key:e.id+20,attrs:{value:e.id}},[t._v(t._s(e.cityName))])})}))],1),t._v(" "),a("FormItem",{attrs:{label:"电话( 选填 )",prop:"phone"}},[a("Input",{attrs:{placeholder:"请输入电话...",clearable:""},model:{value:t.formValidate.phone,callback:function(e){t.$set(t.formValidate,"phone","string"==typeof e?e.trim():e)},expression:"formValidate.phone"}})],1),t._v(" "),a("FormItem",{attrs:{label:"电子邮箱( 选填 )",prop:"email"}},[a("Input",{attrs:{placeholder:"请输入电子邮箱...",clearable:""},model:{value:t.formValidate.email,callback:function(e){t.$set(t.formValidate,"email","string"==typeof e?e.trim():e)},expression:"formValidate.email"}})],1),t._v(" "),a("FormItem",{attrs:{label:"是否启用",prop:"status"}},[a("i-switch",{attrs:{size:"large","true-value":1,"false-value":2},model:{value:t.formValidate.status,callback:function(e){t.$set(t.formValidate,"status",e)},expression:"formValidate.status"}},[a("span",{attrs:{slot:"open"},slot:"open"},[t._v("启用")]),t._v(" "),a("span",{attrs:{slot:"close"},slot:"close"},[t._v("禁用")])])],1)],1),t._v(" "),a("div",{attrs:{slot:"footer"},slot:"footer"},[a("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit("formValidate")}}},[t._v(t._s(t.modify?"修改":"新增"))]),t._v(" "),a("Button",{staticStyle:{"margin-left":"8px"},attrs:{type:"ghost"},on:{click:t.handleReset}},[t._v("取消")])],1)],1)],1)},staticRenderFns:[]};var h={components:{addFrom:a("VU/8")(p,f,!1,function(t){a("9Yxw")},"data-v-3519ca00",null).exports},mixins:[{methods:{loadList:function(t){var e=this;return l()(n.a.mark(function a(){return n.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,e.$ajaxPost(e.projectName+"/staff/finds.do",t);case 2:return a.abrupt("return",a.sent);case 3:case"end":return a.stop()}},a,e)}))()}}}],created:function(){this.loadListData()},data:function(){var t=this;return{canShowAdd:m.a.get("administratorFlag"),isAdd:!1,ismodify:!1,editList:{name:"",phone:"",email:"",password:"",status:1},list:[],loading:!1,columns:[{title:"姓名",key:"name",align:"center"},{title:"角色",key:"roleKey",align:"center",render:function(t,e){return t("div",[t("span","admin"===e.row.roleKey?"1"===e.row.adminFlag?"超级管理员":"管理员":"dataEntry"===e.row.roleKey?"数据录入员":"review"===e.row.roleKey?"审核员":"")])}},{title:"区域",key:"cityName",align:"center"},{title:"电话",key:"phone",align:"center"},{title:"邮箱",key:"email",align:"center"},{title:"是否可管理账户",key:"adminFlag",align:"center",render:function(t,e){return t("div",[t("span",1==+e.row.adminFlag?"可管理":"不可管理")])}},{title:"账号状态",key:"status",align:"center",render:function(t,e){return t("div",[t("span",1==+e.row.status?"启用":"禁用")])}},{title:"操作",key:"action",align:"center",width:250,render:function(e,a){var o=e("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.editRow(a)}}},"修改"),r=e("Button",{props:{type:"ghost",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.$router.push("/info")}}},"移步个人中心"),i=e("Button",{props:{type:"error",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.removeRow(a)}}},"删除"),n=e("Tooltip",{props:{content:"初始化密码后，密码为123456",theme:"light",placement:"top-end"}},[e("Button",{props:{size:"small"},style:{marginRight:"5px"},on:{click:function(){t.resetPasswordRow(a)}}},"初始化密码")]);return e("div",JSON.parse(m.a.get("Temp-User")).name===a.row.name?[r]:[o,i,n])}}],params:{pageNumber:1,pageSize:10},combobox:[],url:{remove:""}}},methods:{addRow:function(){this.isAdd=!0,this.ismodify=!1,this.editList={name:"",phone:"",email:"",password:"",status:1,cityId:"",roleId:""}},editRow:function(t){var e=this;return l()(n.a.mark(function a(){var o;return n.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:e.isAdd=!0,e.ismodify=!0,o=t.row,e.editList={id:o.id,name:o.name,phone:o.phone,email:o.email,status:Number(o.status),cityId:o.cityId,roleId:o.roleId};case 4:case"end":return a.stop()}},a,e)}))()},removeRow:function(t){var e=this;this.$Modal.confirm({title:"提示",content:"<p>确定删除该管理员账户吗？</p>",loading:!0,onOk:function(){e.$ajaxPost(e.projectName+"/staff/delete.do",{id:t.row.id}).then(function(t){e.$Modal.remove(),e.$Message.success("删除成功"),e.loadListData()})}})},resetPasswordRow:function(t){var e=this;this.$Modal.confirm({title:"提示",content:"<p>确定初始化该管理员账户的密码吗？</p>",loading:!0,onOk:function(){e.$ajaxPost(e.projectName+"/staff/initPassword.do",{id:t.row.id}).then(function(t){e.$Modal.remove(),e.$Message.success(t),e.loadListData()})}})},loadListData:function(t){var e=this;this.params=t?r()({},this.params,t):this.params,this.loading=!0,this.loadList(r()({},this.params)).then(function(t){e.loading=!1,e.list=t})},cancelAdd:function(){this.isAdd=!1},callback:function(){this.isAdd=!0}}},v={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("add-from",{attrs:{isAdd:t.isAdd,modify:t.ismodify,formValidate:t.editList},on:{cancelAdd:t.cancelAdd,callback:t.callback,submitAdd:t.loadListData}}),t._v(" "),a("Table",{attrs:{columns:t.columns,data:t.list,loading:t.loading}},[a("Button",{staticClass:"m-marlr",attrs:{slot:"header",type:"ghost"},on:{click:t.addRow},slot:"header"},[t._v("新增")])],1)],1)},staticRenderFns:[]};var g=a("VU/8")(h,v,!1,function(t){a("FQK4")},null,null);e.default=g.exports}});
//# sourceMappingURL=2.03b1eb9e149271cddb41.js.map