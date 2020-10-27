import { message } from 'antd';
import api from 'api'
import { history } from 'umi'

const { createProvider } = api

const Model = {
  namespace: 'newProvider',
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
      const { success } = yield call(createProvider, payload);
      if (success) {
        message.success('提交成功');
        history.goBack();
      }
    },
  },
};
export default Model;
