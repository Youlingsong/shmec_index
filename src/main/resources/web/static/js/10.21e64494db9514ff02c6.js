webpackJsonp([10],{IOUV:function(t,e,r){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=r("Dd8w"),i=r.n(n),o=r("Xxa5"),a=r.n(o),s=r("exGp"),h=r.n(s),u=r("mtWM"),l=r.n(u),c=r("mw3O"),f=r.n(c),d=r("MJLE"),g=r.n(d),p=r("6pd1"),m={data:function(){return{content:{},_id:""}},created:function(){var t=this;return h()(a.a.mark(function e(){return a.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:t._id=t.$route.params.id,t.init();case 2:case"end":return e.stop()}},e,t)}))()},methods:{qrcode:function(){new g.a("qrcode",{width:100,height:100,text:"http://"+window.location.href.split("//")[1].split("/")[0]+this.projectName+"/questionare/mobile/?urlId="+this.content.urlId+"&locker="+p.a.locker,render:"canvas"})},init:function(){var t=this;return h()(a.a.mark(function e(){return a.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:t.getDatas();case 1:case"end":return e.stop()}},e,t)}))()},getDatas:function(){var t=this;return h()(a.a.mark(function e(){var r;return a.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,l.a.post(t.projectName+"/questionare/find.do",f.a.stringify(i()({id:t._id},p.a)));case 2:if(!(r=e.sent).data.status){e.next=5;break}return e.abrupt("return",t.$Message.error(r.data.message));case 5:t.content=r.data.message,t.qrcode();case 7:case"end":return e.stop()}},e,t)}))()},goList:function(){this.$router.push({name:"qaDetailList"})}}},_={render:function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"g-in",staticStyle:{"min-height":"600px"}},[r("div",{staticClass:"main-news-top"},[r("div",{staticClass:"main-top-left"},[r("el-breadcrumb",{attrs:{"separator-class":"el-icon-arrow-right"}},[r("el-breadcrumb-item",{attrs:{to:{path:"/"}}},[t._v("首页")]),t._v(" "),r("el-breadcrumb-item",{attrs:{to:"/qa"}},[t._v("问卷调查")]),t._v(" "),r("el-breadcrumb-item",[t._v("详情")])],1)],1)]),t._v(" "),r("div",{staticClass:"main-news-container clearfix"},[r("Card",{staticClass:"main-news-list"},[r("div",[r("p",{staticClass:"main-list-title"},[t._v(t._s(t.content.name))]),t._v(" "),r("div",{staticClass:"main-list-wrap"},[r("p",{staticClass:"main-summary"},[t._v(t._s(t.content.summary))]),t._v(" "),r("div",{staticClass:"qa-code clearfix"},[r("p",{attrs:{id:"qrcode"}},[t._v("分享二维码：")])]),t._v(" "),r("p",{staticClass:"qa-btn"},[r("a",{staticStyle:{color:"#fff"},attrs:{href:t.projectName+"/questionare/info?urlId="+t.content.urlId}},[t._v("进入问卷")])])])])])],1)])},staticRenderFns:[]};var v=r("VU/8")(m,_,!1,function(t){r("hunn")},"data-v-2a7b3302",null);e.default=v.exports},MJLE:function(t,e,r){var n,i;i=function(){function t(t){this.mode=r.MODE_8BIT_BYTE,this.data=t,this.parsedData=[];for(var e=0,n=this.data.length;e<n;e++){var i=[],o=this.data.charCodeAt(e);o>65536?(i[0]=240|(1835008&o)>>>18,i[1]=128|(258048&o)>>>12,i[2]=128|(4032&o)>>>6,i[3]=128|63&o):o>2048?(i[0]=224|(61440&o)>>>12,i[1]=128|(4032&o)>>>6,i[2]=128|63&o):o>128?(i[0]=192|(1984&o)>>>6,i[1]=128|63&o):i[0]=o,this.parsedData.push(i)}this.parsedData=Array.prototype.concat.apply([],this.parsedData),this.parsedData.length!=this.data.length&&(this.parsedData.unshift(191),this.parsedData.unshift(187),this.parsedData.unshift(239))}function e(t,e){this.typeNumber=t,this.errorCorrectLevel=e,this.modules=null,this.moduleCount=0,this.dataCache=null,this.dataList=[]}t.prototype={getLength:function(t){return this.parsedData.length},write:function(t){for(var e=0,r=this.parsedData.length;e<r;e++)t.put(this.parsedData[e],8)}},e.prototype={addData:function(e){var r=new t(e);this.dataList.push(r),this.dataCache=null},isDark:function(t,e){if(t<0||this.moduleCount<=t||e<0||this.moduleCount<=e)throw new Error(t+","+e);return this.modules[t][e]},getModuleCount:function(){return this.moduleCount},make:function(){this.makeImpl(!1,this.getBestMaskPattern())},makeImpl:function(t,r){this.moduleCount=4*this.typeNumber+17,this.modules=new Array(this.moduleCount);for(var n=0;n<this.moduleCount;n++){this.modules[n]=new Array(this.moduleCount);for(var i=0;i<this.moduleCount;i++)this.modules[n][i]=null}this.setupPositionProbePattern(0,0),this.setupPositionProbePattern(this.moduleCount-7,0),this.setupPositionProbePattern(0,this.moduleCount-7),this.setupPositionAdjustPattern(),this.setupTimingPattern(),this.setupTypeInfo(t,r),this.typeNumber>=7&&this.setupTypeNumber(t),null==this.dataCache&&(this.dataCache=e.createData(this.typeNumber,this.errorCorrectLevel,this.dataList)),this.mapData(this.dataCache,r)},setupPositionProbePattern:function(t,e){for(var r=-1;r<=7;r++)if(!(t+r<=-1||this.moduleCount<=t+r))for(var n=-1;n<=7;n++)e+n<=-1||this.moduleCount<=e+n||(this.modules[t+r][e+n]=0<=r&&r<=6&&(0==n||6==n)||0<=n&&n<=6&&(0==r||6==r)||2<=r&&r<=4&&2<=n&&n<=4)},getBestMaskPattern:function(){for(var t=0,e=0,r=0;r<8;r++){this.makeImpl(!0,r);var n=d.getLostPoint(this);(0==r||t>n)&&(t=n,e=r)}return e},createMovieClip:function(t,e,r){var n=t.createEmptyMovieClip(e,r);this.make();for(var i=0;i<this.modules.length;i++)for(var o=1*i,a=0;a<this.modules[i].length;a++){var s=1*a;this.modules[i][a]&&(n.beginFill(0,100),n.moveTo(s,o),n.lineTo(s+1,o),n.lineTo(s+1,o+1),n.lineTo(s,o+1),n.endFill())}return n},setupTimingPattern:function(){for(var t=8;t<this.moduleCount-8;t++)null==this.modules[t][6]&&(this.modules[t][6]=t%2==0);for(var e=8;e<this.moduleCount-8;e++)null==this.modules[6][e]&&(this.modules[6][e]=e%2==0)},setupPositionAdjustPattern:function(){for(var t=d.getPatternPosition(this.typeNumber),e=0;e<t.length;e++)for(var r=0;r<t.length;r++){var n=t[e],i=t[r];if(null==this.modules[n][i])for(var o=-2;o<=2;o++)for(var a=-2;a<=2;a++)this.modules[n+o][i+a]=-2==o||2==o||-2==a||2==a||0==o&&0==a}},setupTypeNumber:function(t){for(var e=d.getBCHTypeNumber(this.typeNumber),r=0;r<18;r++){var n=!t&&1==(e>>r&1);this.modules[Math.floor(r/3)][r%3+this.moduleCount-8-3]=n}for(r=0;r<18;r++){n=!t&&1==(e>>r&1);this.modules[r%3+this.moduleCount-8-3][Math.floor(r/3)]=n}},setupTypeInfo:function(t,e){for(var r=this.errorCorrectLevel<<3|e,n=d.getBCHTypeInfo(r),i=0;i<15;i++){var o=!t&&1==(n>>i&1);i<6?this.modules[i][8]=o:i<8?this.modules[i+1][8]=o:this.modules[this.moduleCount-15+i][8]=o}for(i=0;i<15;i++){o=!t&&1==(n>>i&1);i<8?this.modules[8][this.moduleCount-i-1]=o:i<9?this.modules[8][15-i-1+1]=o:this.modules[8][15-i-1]=o}this.modules[this.moduleCount-8][8]=!t},mapData:function(t,e){for(var r=-1,n=this.moduleCount-1,i=7,o=0,a=this.moduleCount-1;a>0;a-=2)for(6==a&&a--;;){for(var s=0;s<2;s++)if(null==this.modules[n][a-s]){var h=!1;o<t.length&&(h=1==(t[o]>>>i&1)),d.getMask(e,n,a-s)&&(h=!h),this.modules[n][a-s]=h,-1==--i&&(o++,i=7)}if((n+=r)<0||this.moduleCount<=n){n-=r,r=-r;break}}}},e.PAD0=236,e.PAD1=17,e.createData=function(t,r,n){for(var i=_.getRSBlocks(t,r),o=new v,a=0;a<n.length;a++){var s=n[a];o.put(s.mode,4),o.put(s.getLength(),d.getLengthInBits(s.mode,t)),s.write(o)}var h=0;for(a=0;a<i.length;a++)h+=i[a].dataCount;if(o.getLengthInBits()>8*h)throw new Error("code length overflow. ("+o.getLengthInBits()+">"+8*h+")");for(o.getLengthInBits()+4<=8*h&&o.put(0,4);o.getLengthInBits()%8!=0;)o.putBit(!1);for(;!(o.getLengthInBits()>=8*h||(o.put(e.PAD0,8),o.getLengthInBits()>=8*h));)o.put(e.PAD1,8);return e.createBytes(o,i)},e.createBytes=function(t,e){for(var r=0,n=0,i=0,o=new Array(e.length),a=new Array(e.length),s=0;s<e.length;s++){var h=e[s].dataCount,u=e[s].totalCount-h;n=Math.max(n,h),i=Math.max(i,u),o[s]=new Array(h);for(var l=0;l<o[s].length;l++)o[s][l]=255&t.buffer[l+r];r+=h;var c=d.getErrorCorrectPolynomial(u),f=new m(o[s],c.getLength()-1).mod(c);a[s]=new Array(c.getLength()-1);for(l=0;l<a[s].length;l++){var g=l+f.getLength()-a[s].length;a[s][l]=g>=0?f.get(g):0}}var p=0;for(l=0;l<e.length;l++)p+=e[l].totalCount;var _=new Array(p),v=0;for(l=0;l<n;l++)for(s=0;s<e.length;s++)l<o[s].length&&(_[v++]=o[s][l]);for(l=0;l<i;l++)for(s=0;s<e.length;s++)l<a[s].length&&(_[v++]=a[s][l]);return _};for(var r={MODE_NUMBER:1,MODE_ALPHA_NUM:2,MODE_8BIT_BYTE:4,MODE_KANJI:8},i={L:1,M:0,Q:3,H:2},o=0,a=1,s=2,h=3,u=4,l=5,c=6,f=7,d={PATTERN_POSITION_TABLE:[[],[6,18],[6,22],[6,26],[6,30],[6,34],[6,22,38],[6,24,42],[6,26,46],[6,28,50],[6,30,54],[6,32,58],[6,34,62],[6,26,46,66],[6,26,48,70],[6,26,50,74],[6,30,54,78],[6,30,56,82],[6,30,58,86],[6,34,62,90],[6,28,50,72,94],[6,26,50,74,98],[6,30,54,78,102],[6,28,54,80,106],[6,32,58,84,110],[6,30,58,86,114],[6,34,62,90,118],[6,26,50,74,98,122],[6,30,54,78,102,126],[6,26,52,78,104,130],[6,30,56,82,108,134],[6,34,60,86,112,138],[6,30,58,86,114,142],[6,34,62,90,118,146],[6,30,54,78,102,126,150],[6,24,50,76,102,128,154],[6,28,54,80,106,132,158],[6,32,58,84,110,136,162],[6,26,54,82,110,138,166],[6,30,58,86,114,142,170]],G15:1335,G18:7973,G15_MASK:21522,getBCHTypeInfo:function(t){for(var e=t<<10;d.getBCHDigit(e)-d.getBCHDigit(d.G15)>=0;)e^=d.G15<<d.getBCHDigit(e)-d.getBCHDigit(d.G15);return(t<<10|e)^d.G15_MASK},getBCHTypeNumber:function(t){for(var e=t<<12;d.getBCHDigit(e)-d.getBCHDigit(d.G18)>=0;)e^=d.G18<<d.getBCHDigit(e)-d.getBCHDigit(d.G18);return t<<12|e},getBCHDigit:function(t){for(var e=0;0!=t;)e++,t>>>=1;return e},getPatternPosition:function(t){return d.PATTERN_POSITION_TABLE[t-1]},getMask:function(t,e,r){switch(t){case o:return(e+r)%2==0;case a:return e%2==0;case s:return r%3==0;case h:return(e+r)%3==0;case u:return(Math.floor(e/2)+Math.floor(r/3))%2==0;case l:return e*r%2+e*r%3==0;case c:return(e*r%2+e*r%3)%2==0;case f:return(e*r%3+(e+r)%2)%2==0;default:throw new Error("bad maskPattern:"+t)}},getErrorCorrectPolynomial:function(t){for(var e=new m([1],0),r=0;r<t;r++)e=e.multiply(new m([1,g.gexp(r)],0));return e},getLengthInBits:function(t,e){if(1<=e&&e<10)switch(t){case r.MODE_NUMBER:return 10;case r.MODE_ALPHA_NUM:return 9;case r.MODE_8BIT_BYTE:case r.MODE_KANJI:return 8;default:throw new Error("mode:"+t)}else if(e<27)switch(t){case r.MODE_NUMBER:return 12;case r.MODE_ALPHA_NUM:return 11;case r.MODE_8BIT_BYTE:return 16;case r.MODE_KANJI:return 10;default:throw new Error("mode:"+t)}else{if(!(e<41))throw new Error("type:"+e);switch(t){case r.MODE_NUMBER:return 14;case r.MODE_ALPHA_NUM:return 13;case r.MODE_8BIT_BYTE:return 16;case r.MODE_KANJI:return 12;default:throw new Error("mode:"+t)}}},getLostPoint:function(t){for(var e=t.getModuleCount(),r=0,n=0;n<e;n++)for(var i=0;i<e;i++){for(var o=0,a=t.isDark(n,i),s=-1;s<=1;s++)if(!(n+s<0||e<=n+s))for(var h=-1;h<=1;h++)i+h<0||e<=i+h||0==s&&0==h||a==t.isDark(n+s,i+h)&&o++;o>5&&(r+=3+o-5)}for(n=0;n<e-1;n++)for(i=0;i<e-1;i++){var u=0;t.isDark(n,i)&&u++,t.isDark(n+1,i)&&u++,t.isDark(n,i+1)&&u++,t.isDark(n+1,i+1)&&u++,0!=u&&4!=u||(r+=3)}for(n=0;n<e;n++)for(i=0;i<e-6;i++)t.isDark(n,i)&&!t.isDark(n,i+1)&&t.isDark(n,i+2)&&t.isDark(n,i+3)&&t.isDark(n,i+4)&&!t.isDark(n,i+5)&&t.isDark(n,i+6)&&(r+=40);for(i=0;i<e;i++)for(n=0;n<e-6;n++)t.isDark(n,i)&&!t.isDark(n+1,i)&&t.isDark(n+2,i)&&t.isDark(n+3,i)&&t.isDark(n+4,i)&&!t.isDark(n+5,i)&&t.isDark(n+6,i)&&(r+=40);var l=0;for(i=0;i<e;i++)for(n=0;n<e;n++)t.isDark(n,i)&&l++;return r+=10*(Math.abs(100*l/e/e-50)/5)}},g={glog:function(t){if(t<1)throw new Error("glog("+t+")");return g.LOG_TABLE[t]},gexp:function(t){for(;t<0;)t+=255;for(;t>=256;)t-=255;return g.EXP_TABLE[t]},EXP_TABLE:new Array(256),LOG_TABLE:new Array(256)},p=0;p<8;p++)g.EXP_TABLE[p]=1<<p;for(p=8;p<256;p++)g.EXP_TABLE[p]=g.EXP_TABLE[p-4]^g.EXP_TABLE[p-5]^g.EXP_TABLE[p-6]^g.EXP_TABLE[p-8];for(p=0;p<255;p++)g.LOG_TABLE[g.EXP_TABLE[p]]=p;function m(t,e){if(void 0==t.length)throw new Error(t.length+"/"+e);for(var r=0;r<t.length&&0==t[r];)r++;this.num=new Array(t.length-r+e);for(var n=0;n<t.length-r;n++)this.num[n]=t[n+r]}function _(t,e){this.totalCount=t,this.dataCount=e}function v(){this.buffer=[],this.length=0}m.prototype={get:function(t){return this.num[t]},getLength:function(){return this.num.length},multiply:function(t){for(var e=new Array(this.getLength()+t.getLength()-1),r=0;r<this.getLength();r++)for(var n=0;n<t.getLength();n++)e[r+n]^=g.gexp(g.glog(this.get(r))+g.glog(t.get(n)));return new m(e,0)},mod:function(t){if(this.getLength()-t.getLength()<0)return this;for(var e=g.glog(this.get(0))-g.glog(t.get(0)),r=new Array(this.getLength()),n=0;n<this.getLength();n++)r[n]=this.get(n);for(n=0;n<t.getLength();n++)r[n]^=g.gexp(g.glog(t.get(n))+e);return new m(r,0).mod(t)}},_.RS_BLOCK_TABLE=[[1,26,19],[1,26,16],[1,26,13],[1,26,9],[1,44,34],[1,44,28],[1,44,22],[1,44,16],[1,70,55],[1,70,44],[2,35,17],[2,35,13],[1,100,80],[2,50,32],[2,50,24],[4,25,9],[1,134,108],[2,67,43],[2,33,15,2,34,16],[2,33,11,2,34,12],[2,86,68],[4,43,27],[4,43,19],[4,43,15],[2,98,78],[4,49,31],[2,32,14,4,33,15],[4,39,13,1,40,14],[2,121,97],[2,60,38,2,61,39],[4,40,18,2,41,19],[4,40,14,2,41,15],[2,146,116],[3,58,36,2,59,37],[4,36,16,4,37,17],[4,36,12,4,37,13],[2,86,68,2,87,69],[4,69,43,1,70,44],[6,43,19,2,44,20],[6,43,15,2,44,16],[4,101,81],[1,80,50,4,81,51],[4,50,22,4,51,23],[3,36,12,8,37,13],[2,116,92,2,117,93],[6,58,36,2,59,37],[4,46,20,6,47,21],[7,42,14,4,43,15],[4,133,107],[8,59,37,1,60,38],[8,44,20,4,45,21],[12,33,11,4,34,12],[3,145,115,1,146,116],[4,64,40,5,65,41],[11,36,16,5,37,17],[11,36,12,5,37,13],[5,109,87,1,110,88],[5,65,41,5,66,42],[5,54,24,7,55,25],[11,36,12],[5,122,98,1,123,99],[7,73,45,3,74,46],[15,43,19,2,44,20],[3,45,15,13,46,16],[1,135,107,5,136,108],[10,74,46,1,75,47],[1,50,22,15,51,23],[2,42,14,17,43,15],[5,150,120,1,151,121],[9,69,43,4,70,44],[17,50,22,1,51,23],[2,42,14,19,43,15],[3,141,113,4,142,114],[3,70,44,11,71,45],[17,47,21,4,48,22],[9,39,13,16,40,14],[3,135,107,5,136,108],[3,67,41,13,68,42],[15,54,24,5,55,25],[15,43,15,10,44,16],[4,144,116,4,145,117],[17,68,42],[17,50,22,6,51,23],[19,46,16,6,47,17],[2,139,111,7,140,112],[17,74,46],[7,54,24,16,55,25],[34,37,13],[4,151,121,5,152,122],[4,75,47,14,76,48],[11,54,24,14,55,25],[16,45,15,14,46,16],[6,147,117,4,148,118],[6,73,45,14,74,46],[11,54,24,16,55,25],[30,46,16,2,47,17],[8,132,106,4,133,107],[8,75,47,13,76,48],[7,54,24,22,55,25],[22,45,15,13,46,16],[10,142,114,2,143,115],[19,74,46,4,75,47],[28,50,22,6,51,23],[33,46,16,4,47,17],[8,152,122,4,153,123],[22,73,45,3,74,46],[8,53,23,26,54,24],[12,45,15,28,46,16],[3,147,117,10,148,118],[3,73,45,23,74,46],[4,54,24,31,55,25],[11,45,15,31,46,16],[7,146,116,7,147,117],[21,73,45,7,74,46],[1,53,23,37,54,24],[19,45,15,26,46,16],[5,145,115,10,146,116],[19,75,47,10,76,48],[15,54,24,25,55,25],[23,45,15,25,46,16],[13,145,115,3,146,116],[2,74,46,29,75,47],[42,54,24,1,55,25],[23,45,15,28,46,16],[17,145,115],[10,74,46,23,75,47],[10,54,24,35,55,25],[19,45,15,35,46,16],[17,145,115,1,146,116],[14,74,46,21,75,47],[29,54,24,19,55,25],[11,45,15,46,46,16],[13,145,115,6,146,116],[14,74,46,23,75,47],[44,54,24,7,55,25],[59,46,16,1,47,17],[12,151,121,7,152,122],[12,75,47,26,76,48],[39,54,24,14,55,25],[22,45,15,41,46,16],[6,151,121,14,152,122],[6,75,47,34,76,48],[46,54,24,10,55,25],[2,45,15,64,46,16],[17,152,122,4,153,123],[29,74,46,14,75,47],[49,54,24,10,55,25],[24,45,15,46,46,16],[4,152,122,18,153,123],[13,74,46,32,75,47],[48,54,24,14,55,25],[42,45,15,32,46,16],[20,147,117,4,148,118],[40,75,47,7,76,48],[43,54,24,22,55,25],[10,45,15,67,46,16],[19,148,118,6,149,119],[18,75,47,31,76,48],[34,54,24,34,55,25],[20,45,15,61,46,16]],_.getRSBlocks=function(t,e){var r=_.getRsBlockTable(t,e);if(void 0==r)throw new Error("bad rs block @ typeNumber:"+t+"/errorCorrectLevel:"+e);for(var n=r.length/3,i=[],o=0;o<n;o++)for(var a=r[3*o+0],s=r[3*o+1],h=r[3*o+2],u=0;u<a;u++)i.push(new _(s,h));return i},_.getRsBlockTable=function(t,e){switch(e){case i.L:return _.RS_BLOCK_TABLE[4*(t-1)+0];case i.M:return _.RS_BLOCK_TABLE[4*(t-1)+1];case i.Q:return _.RS_BLOCK_TABLE[4*(t-1)+2];case i.H:return _.RS_BLOCK_TABLE[4*(t-1)+3];default:return}},v.prototype={get:function(t){var e=Math.floor(t/8);return 1==(this.buffer[e]>>>7-t%8&1)},put:function(t,e){for(var r=0;r<e;r++)this.putBit(1==(t>>>e-r-1&1))},getLengthInBits:function(){return this.length},putBit:function(t){var e=Math.floor(this.length/8);this.buffer.length<=e&&this.buffer.push(0),t&&(this.buffer[e]|=128>>>this.length%8),this.length++}};var w=[[17,14,11,7],[32,26,20,14],[53,42,32,24],[78,62,46,34],[106,84,60,44],[134,106,74,58],[154,122,86,64],[192,152,108,84],[230,180,130,98],[271,213,151,119],[321,251,177,137],[367,287,203,155],[425,331,241,177],[458,362,258,194],[520,412,292,220],[586,450,322,250],[644,504,364,280],[718,560,394,310],[792,624,442,338],[858,666,482,382],[929,711,509,403],[1003,779,565,439],[1091,857,611,461],[1171,911,661,511],[1273,997,715,535],[1367,1059,751,593],[1465,1125,805,625],[1528,1190,868,658],[1628,1264,908,698],[1732,1370,982,742],[1840,1452,1030,790],[1952,1538,1112,842],[2068,1628,1168,898],[2188,1722,1228,958],[2303,1809,1283,983],[2431,1911,1351,1051],[2563,1989,1423,1093],[2699,2099,1499,1139],[2809,2213,1579,1219],[2953,2331,1663,1273]];function C(){var t=!1,e=navigator.userAgent;if(/android/i.test(e)){t=!0;var r=e.toString().match(/android ([0-9]\.[0-9])/i);r&&r[1]&&(t=parseFloat(r[1]))}return t}var D=function(){var t=function(t,e){this._el=t,this._htOption=e};return t.prototype.draw=function(t){var e=this._htOption,r=this._el,n=t.getModuleCount();Math.floor(e.width/n),Math.floor(e.height/n);function i(t,e){var r=document.createElementNS("http://www.w3.org/2000/svg",t);for(var n in e)e.hasOwnProperty(n)&&r.setAttribute(n,e[n]);return r}this.clear();var o=i("svg",{viewBox:"0 0 "+String(n)+" "+String(n),width:"100%",height:"100%",fill:e.colorLight});o.setAttributeNS("http://www.w3.org/2000/xmlns/","xmlns:xlink","http://www.w3.org/1999/xlink"),r.appendChild(o),o.appendChild(i("rect",{fill:e.colorLight,width:"100%",height:"100%"})),o.appendChild(i("rect",{fill:e.colorDark,width:"1",height:"1",id:"template"}));for(var a=0;a<n;a++)for(var s=0;s<n;s++)if(t.isDark(a,s)){var h=i("use",{x:String(s),y:String(a)});h.setAttributeNS("http://www.w3.org/1999/xlink","href","#template"),o.appendChild(h)}},t.prototype.clear=function(){for(;this._el.hasChildNodes();)this._el.removeChild(this._el.lastChild)},t}(),A="svg"===document.documentElement.tagName.toLowerCase()?D:"undefined"==typeof CanvasRenderingContext2D?function(){var t=function(t,e){this._el=t,this._htOption=e};return t.prototype.draw=function(t){for(var e=this._htOption,r=this._el,n=t.getModuleCount(),i=Math.floor(e.width/n),o=Math.floor(e.height/n),a=['<table style="border:0;border-collapse:collapse;">'],s=0;s<n;s++){a.push("<tr>");for(var h=0;h<n;h++)a.push('<td style="border:0;border-collapse:collapse;padding:0;margin:0;width:'+i+"px;height:"+o+"px;background-color:"+(t.isDark(s,h)?e.colorDark:e.colorLight)+';"></td>');a.push("</tr>")}a.push("</table>"),r.innerHTML=a.join("");var u=r.childNodes[0],l=(e.width-u.offsetWidth)/2,c=(e.height-u.offsetHeight)/2;l>0&&c>0&&(u.style.margin=c+"px "+l+"px")},t.prototype.clear=function(){this._el.innerHTML=""},t}():function(){function t(){this._elImage.src=this._elCanvas.toDataURL("image/png"),this._elImage.style.display="block",this._elCanvas.style.display="none"}if(this._android&&this._android<=2.1){var e=1/window.devicePixelRatio,r=CanvasRenderingContext2D.prototype.drawImage;CanvasRenderingContext2D.prototype.drawImage=function(t,n,i,o,a,s,h,u,l){if("nodeName"in t&&/img/i.test(t.nodeName))for(var c=arguments.length-1;c>=1;c--)arguments[c]=arguments[c]*e;else void 0===u&&(arguments[1]*=e,arguments[2]*=e,arguments[3]*=e,arguments[4]*=e);r.apply(this,arguments)}}var n=function(t,e){this._bIsPainted=!1,this._android=C(),this._htOption=e,this._elCanvas=document.createElement("canvas"),this._elCanvas.width=e.width,this._elCanvas.height=e.height,t.appendChild(this._elCanvas),this._el=t,this._oContext=this._elCanvas.getContext("2d"),this._bIsPainted=!1,this._elImage=document.createElement("img"),this._elImage.alt="Scan me!",this._elImage.style.display="none",this._el.appendChild(this._elImage),this._bSupportDataURI=null};return n.prototype.draw=function(t){var e=this._elImage,r=this._oContext,n=this._htOption,i=t.getModuleCount(),o=n.width/i,a=n.height/i,s=Math.round(o),h=Math.round(a);e.style.display="none",this.clear();for(var u=0;u<i;u++)for(var l=0;l<i;l++){var c=t.isDark(u,l),f=l*o,d=u*a;r.strokeStyle=c?n.colorDark:n.colorLight,r.lineWidth=1,r.fillStyle=c?n.colorDark:n.colorLight,r.fillRect(f,d,o,a),r.strokeRect(Math.floor(f)+.5,Math.floor(d)+.5,s,h),r.strokeRect(Math.ceil(f)-.5,Math.ceil(d)-.5,s,h)}this._bIsPainted=!0},n.prototype.makeImage=function(){this._bIsPainted&&function(t,e){var r=this;if(r._fFail=e,r._fSuccess=t,null===r._bSupportDataURI){var n=document.createElement("img"),i=function(){r._bSupportDataURI=!1,r._fFail&&r._fFail.call(r)};return n.onabort=i,n.onerror=i,n.onload=function(){r._bSupportDataURI=!0,r._fSuccess&&r._fSuccess.call(r)},void(n.src="data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==")}!0===r._bSupportDataURI&&r._fSuccess?r._fSuccess.call(r):!1===r._bSupportDataURI&&r._fFail&&r._fFail.call(r)}.call(this,t)},n.prototype.isPainted=function(){return this._bIsPainted},n.prototype.clear=function(){this._oContext.clearRect(0,0,this._elCanvas.width,this._elCanvas.height),this._bIsPainted=!1},n.prototype.round=function(t){return t?Math.floor(1e3*t)/1e3:t},n}();function L(t,e){for(var r=1,n=function(t){var e=encodeURI(t).toString().replace(/\%[0-9a-fA-F]{2}/g,"a");return e.length+(e.length!=t?3:0)}(t),o=0,a=w.length;o<=a;o++){var s=0;switch(e){case i.L:s=w[o][0];break;case i.M:s=w[o][1];break;case i.Q:s=w[o][2];break;case i.H:s=w[o][3]}if(n<=s)break;r++}if(r>w.length)throw new Error("Too long data");return r}return(n=function(t,e){if(this._htOption={width:256,height:256,typeNumber:4,colorDark:"#000000",colorLight:"#ffffff",correctLevel:i.H},"string"==typeof e&&(e={text:e}),e)for(var r in e)this._htOption[r]=e[r];"string"==typeof t&&(t=document.getElementById(t)),this._htOption.useSVG&&(A=D),this._android=C(),this._el=t,this._oQRCode=null,this._oDrawing=new A(this._el,this._htOption),this._htOption.text&&this.makeCode(this._htOption.text)}).prototype.makeCode=function(t){this._oQRCode=new e(L(t,this._htOption.correctLevel),this._htOption.correctLevel),this._oQRCode.addData(t),this._oQRCode.make(),this._el.title=t,this._oDrawing.draw(this._oQRCode),this.makeImage()},n.prototype.makeImage=function(){"function"==typeof this._oDrawing.makeImage&&(!this._android||this._android>=3)&&this._oDrawing.makeImage()},n.prototype.clear=function(){this._oDrawing.clear()},n.CorrectLevel=i,n},t.exports=i()},hunn:function(t,e){}});
//# sourceMappingURL=10.21e64494db9514ff02c6.js.map