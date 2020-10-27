import { message } from 'antd';
import api from 'api'
import { history } from 'umi'

const { createHotel } = api

const Model = {
  namespace: 'newHotel',
  state: {
  },
  reducers: {
    //处理查询结果，合并到state中
    querySuccess(state, { payload }) {
      return {
        ...state,
        ...payload,
      }
    },
  },
  effects: {
    *new({ payload }, { call }) {
      console.log(payload)
      try{
      const { success } = yield call(createHotel, payload);
      if (success) {
        message.success('提交成功');
        history.goBack();
      }
      }catch(e){
        console.log(e)
      }
    },
  },
};
export default Model;
