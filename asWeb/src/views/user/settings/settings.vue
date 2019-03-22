<template>
    <article>
        <div class="settings">
            <div class="settings-header">
                <el-menu :default-active="$route.path" active-text-color="#409EFF" mode="horizontal" :router="true">
                    <!--<el-menu-item index="/microPage">微主页设置</el-menu-item>-->
                    <!--<el-menu-item index="/informationClass">资讯产品分类设置</el-menu-item>-->
                    <el-menu-item index="/userSetting">账号管理</el-menu-item>
                    <!--<el-menu-item index="/settings">系统设置</el-menu-item>-->
                </el-menu>
            </div>
            <div class="settings-body">
              <user-settings />
            </div>
        </div>
    </article>
</template>
<script>
import userSettings from '@/views/user/settings/userSettings'
import { getCookie } from "@/util/cookie";
import VDistpicker from 'v-distpicker'
export default {
    components: { VDistpicker, userSettings },
    data(){
        return{
            dayNum:1,
            couponNum:0,
            // 上传图片地址
            uploadImagUrl: '',

            options: [{id: '0',label: '每天'},{id: '1',label: '每周'},{id: '2',label: '每月'}],
            dateType:'',

            // 表单
            setForm:{orgName:'',logo_path:'', province:'',city:''},
            ruleSetForm:{
                orgName: [
                    { required: true, message: '请输入机构名称', trigger: 'blur' }
                ]
            },
            customerId: sessionStorage.getItem('customerId'),
            organId:'',
            token: sessionStorage.getItem('token')
        }
    },
    mounted:function(){
        this.getOrganInfo();
    },
    methods: {
        // Logo上传
        // 上传图片到服务器
        submitUpload() {
            this.$refs.upload.submit();
        },
        // 返回图片地址
        uploadImg: function(res, file) {
            if (res.code == 200) {
                this.$message({
                    message: '上传成功',
                    type: 'success'
                });
                this.setForm.logo_path = res.data.url;
            }else{
                this.$message.error('上传LOGO失败！');
            }
        },
        // 图片大小
        beforeImgUpload(file) {
            const isLt200KB = file.size / 1024 < 200;

            if (!isLt200KB) {
                this.$message.error('上传LOGO图片大小不能超过 200KB!');
            }
            return isLt200KB;
        },
        // 系统设置
        submitForm:function(formName){
             this.$refs[formName].validate((valid) => {
                if (valid) {
                    let data = {
                        id: this.organId,
                        csId: this.customerId,
                        name: this.setForm.orgName,
                        file: this.setForm.logo_path,
                        province: this.setForm.province,
                        city: this.setForm.city,
                        subscribeRemind:this.dayNum,
                        couponLimitType:this.dateType,
                        couponLimitNum:this.couponNum
                    };
                    this.axios.post('/organ/updateOrganInfo', this.$qs.stringify(data))
                    .then(jsondata => { //成功
                        if(jsondata.data.code == 200){
                            this.$message.success('系统设置成功');
                            //this.$router.go(0)
                        }
                    }).catch(e => { //失败
                        this.$message.error('设置失败');
                        console.log(e)
                    })
                } else {
                    return false;
                }
            });
        },
        // 获取机构初始化信息
        getOrganInfo:function(){

            let organData = JSON.stringify(sessionStorage.getItem('organInfo'));
            let data = JSON.parse($.parseJSON(organData));
            //配置圖片上傳地址
            if (data == null){
                this.uploadImagUrl = this.$apiUrl + '/file/upload?subject=organ&target=1';
                return
            }
            //--
            this.setForm.orgName = data.organName;
            this.setForm.logo_path = data.logo;
            this.setForm.province = data.province;
            this.setForm.city = data.city;
            this.dayNum = data.subscribeRemind;
            this.dateType = this.options[data.couponLimitType].id;
            this.couponNum = data.couponLimitNum;
            // 机构ID
            this.organId = data.organId;
            if (data.organId == undefined && data.organId == null && data.organId == '') {
                this.uploadImagUrl = this.$apiUrl + '/file/upload?subject=organ&target=1';
            } else {
                this.uploadImagUrl = this.$apiUrl + '/file/upload?subject=organ&target=' + data.organId;
            }
        },
        onChangeCity(data){
            this.setForm.city = data.value
        }
    }
}
</script>
<style lang="scss">
    .settings-header{
        .el-menu--horizontal>.el-menu-item.is-active{color:#409EFF;}
    }
    .settings-body{
        .avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 168px;
            height: 168px;
            line-height: 168px;
            text-align: center;
            border: 1px dashed #CCC;
        }
        .avatar-uploader-icon:hover{border-color: #409EFF;}
        .avatar {
            width: 168px;
            height: 168px;
            display: block;
        }
    }
</style>

<style lang="scss" scoped>
    .settingForm{padding:.4rem 0 0;width:60%;
        .el-upload__tip{color:#999;}
    }

    .otherSet{border-top:solid 1px #CCC;padding:30px 0 0;
        .el-row{padding:20px 10px;}
        .el-col{padding:10px 0}
    }
</style>
