"use strict";(self.webpackChunkexamenfront=self.webpackChunkexamenfront||[]).push([[111],{2111:(F,g,c)=>{c.r(g),c.d(g,{ExceptionModule:()=>B});var p=c(6895),t=c(4650),C=c(7579),m=c(2722),E=c(8797),l=c(9094),b=c(1481),z=c(4913),k=c(445),x=c(9643),r=c(9132),a=c(6616),y=c(7044),f=c(1811);const M=["conTpl"];function D(n,i){if(1&n&&(t.TgZ(0,"button",9),t._uU(1),t.qZA()),2&n){const e=t.oxw();t.Q6J("routerLink",e.backRouterLink)("nzType","primary"),t.xp6(1),t.hij(" ",e.locale.backToHome," ")}}const Z=["*"];let v=(()=>{class n{set type(e){const o=this.typeDict[e];o&&(this.fixImg(o.img),this._type=e,this._title=o.title,this._desc="")}fixImg(e){this._img=this.dom.bypassSecurityTrustStyle(`url('${e}')`)}set img(e){this.fixImg(e)}set title(e){this._title=this.dom.bypassSecurityTrustHtml(e)}set desc(e){this._desc=this.dom.bypassSecurityTrustHtml(e)}checkContent(){this.hasCon=!(0,E.xb)(this.conTpl.nativeElement),this.cdr.detectChanges()}constructor(e,o,s,d,h){this.i18n=e,this.dom=o,this.directionality=d,this.cdr=h,this.destroy$=new C.x,this.locale={},this.hasCon=!1,this.dir="ltr",this._img="",this._title="",this._desc="",this.backRouterLink="/",s.attach(this,"exception",{typeDict:{403:{img:"https://gw.alipayobjects.com/zos/rmsportal/wZcnGqRDyhPOEYFcZDnb.svg",title:"403"},404:{img:"https://gw.alipayobjects.com/zos/rmsportal/KpnpchXsobRgLElEozzI.svg",title:"404"},500:{img:"https://gw.alipayobjects.com/zos/rmsportal/RVRUAYdCGeYNBWoKiIwB.svg",title:"500"}}})}ngOnInit(){this.dir=this.directionality.value,this.directionality.change?.pipe((0,m.R)(this.destroy$)).subscribe(e=>{this.dir=e}),this.i18n.change.pipe((0,m.R)(this.destroy$)).subscribe(()=>this.locale=this.i18n.getData("exception")),this.checkContent()}ngOnDestroy(){this.destroy$.next(),this.destroy$.complete()}}return n.\u0275fac=function(e){return new(e||n)(t.Y36(l.s7),t.Y36(b.H7),t.Y36(z.Ri),t.Y36(k.Is,8),t.Y36(t.sBO))},n.\u0275cmp=t.Xpm({type:n,selectors:[["exception"]],viewQuery:function(e,o){if(1&e&&t.Gf(M,7),2&e){let s;t.iGM(s=t.CRH())&&(o.conTpl=s.first)}},hostVars:4,hostBindings:function(e,o){2&e&&t.ekj("exception",!0)("exception-rtl","rtl"===o.dir)},inputs:{type:"type",img:"img",title:"title",desc:"desc",backRouterLink:"backRouterLink"},exportAs:["exception"],ngContentSelectors:Z,decls:10,vars:5,consts:[[1,"exception__img-block"],[1,"exception__img"],[1,"exception__cont"],[1,"exception__cont-title",3,"innerHTML"],[1,"exception__cont-desc",3,"innerHTML"],[1,"exception__cont-actions"],[3,"cdkObserveContent"],["conTpl",""],["nz-button","",3,"routerLink","nzType",4,"ngIf"],["nz-button","",3,"routerLink","nzType"]],template:function(e,o){1&e&&(t.F$t(),t.TgZ(0,"div",0),t._UZ(1,"div",1),t.qZA(),t.TgZ(2,"div",2),t._UZ(3,"h1",3)(4,"div",4),t.TgZ(5,"div",5)(6,"div",6,7),t.NdJ("cdkObserveContent",function(){return o.checkContent()}),t.Hsn(8),t.qZA(),t.YNc(9,D,2,3,"button",8),t.qZA()()),2&e&&(t.xp6(1),t.Udp("background-image",o._img),t.xp6(2),t.Q6J("innerHTML",o._title,t.oJD),t.xp6(1),t.Q6J("innerHTML",o._desc||o.locale[o._type],t.oJD),t.xp6(5),t.Q6J("ngIf",!o.hasCon))},dependencies:[p.O5,x.wD,r.rH,a.ix,y.w,f.dQ],encapsulation:2,changeDetection:0}),n})(),R=(()=>{class n{}return n.\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({imports:[p.ez,x.Q8,r.Bz,l.lD,a.sL]}),n})();var T=c(1971);let u=(()=>{class n{get type(){return this.route.snapshot.data.type}constructor(e){this.route=e}}return n.\u0275fac=function(e){return new(e||n)(t.Y36(r.gz))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-exception"]],decls:1,vars:1,consts:[[2,"min-height","500px","height","80%",3,"type"]],template:function(e,o){1&e&&t._UZ(0,"exception",0),2&e&&t.Q6J("type",o.type)},dependencies:[v],encapsulation:2,changeDetection:0}),n})();var J=c(7128);function L(n,i){if(1&n){const e=t.EpF();t.TgZ(0,"button",3),t.NdJ("click",function(){const d=t.CHM(e).$implicit,h=t.oxw();return t.KtG(h.go(d))}),t._uU(1),t.qZA()}if(2&n){const e=i.$implicit;t.xp6(1),t.hij("\u89e6\u53d1",e,"")}}const H=[{path:"403",component:u,data:{type:403}},{path:"404",component:u,data:{type:404}},{path:"500",component:u,data:{type:500}},{path:"trigger",component:(()=>{class n{constructor(e,o){this.http=e,this.tokenService=o,this.types=[401,403,404,500]}go(e){this.http.get(`/api/${e}`).subscribe()}refresh(){this.tokenService.set({token:"invalid-token"}),this.http.post("https://localhost:5001/auth").subscribe(e=>console.warn("\u6210\u529f",e),e=>{console.log("\u6700\u540e\u7ed3\u679c\u5931\u8d25",e)})}}return n.\u0275fac=function(e){return new(e||n)(t.Y36(l.lP),t.Y36(J.T))},n.\u0275cmp=t.Xpm({type:n,selectors:[["exception-trigger"]],decls:5,vars:1,consts:[[1,"pt-lg"],["nz-button","","nzDanger","",3,"click",4,"ngFor","ngForOf"],["nz-button","","nzType","link",3,"click"],["nz-button","","nzDanger","",3,"click"]],template:function(e,o){1&e&&(t.TgZ(0,"div",0)(1,"nz-card"),t.YNc(2,L,2,1,"button",1),t.TgZ(3,"button",2),t.NdJ("click",function(){return o.refresh()}),t._uU(4,"\u89e6\u53d1\u5237\u65b0Token"),t.qZA()()()),2&e&&(t.xp6(2),t.Q6J("ngForOf",o.types))},dependencies:[p.sg,a.ix,y.w,f.dQ,T.bd],encapsulation:2}),n})()}];let Y=(()=>{class n{}return n.\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({imports:[r.Bz.forChild(H),r.Bz]}),n})(),B=(()=>{class n{}return n.\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({imports:[p.ez,R,a.sL,T.vh,Y]}),n})()}}]);