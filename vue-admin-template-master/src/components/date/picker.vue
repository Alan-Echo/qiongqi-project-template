<template>
    <el-date-picker
        v-model="dateValue"
        type="datetimerange"
        :picker-options="pickerOptions"
        @change="change"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        value-format="yyyy-MM-dd HH:mm:ss"
        align="right">
    </el-date-picker>
</template>

<script>
export default {
    props:{
        value:{
            type:Object
        }
    },
    data(){
        return{
            dateValue:[],
            pickerOptions:{
                shortcuts: [
                    {
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, 
                    {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, 
                    {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }
                ]
            }
        }
    },
    methods:{
        change(){
            let that = this;
            if(this.dateValue!=null&&this.dateValue.length>0){
                this.$set(this.value,'startTime',this.dateValue[0])
                this.$set(this.value,'endTime',this.dateValue[1])
            }else{
                this.value.startTime=""
                this.value.endTime=""
            }
        },
    },

}
</script>

<style>

</style>