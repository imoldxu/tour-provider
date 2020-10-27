
import api from 'api'
const { pathToRegexp } = require("path-to-regexp")

const { queryTeam } = api

export default {
    namespace: 'teamDetail',

    state: {
        guide: {},
        driver: {},
        tourist: [],
    },

    subscriptions: {
        //监听路径的变化，跳转之后发起查询操作
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/team/:id').exec(pathname)
                if (match) {
                    dispatch({ type: 'query', payload: { id: match[1] } })
                }
            })
        },
    },

    reducers: {
        //处理查询结果，合并到state中
        querySuccess(state, { payload }) {
            let newstate = {
                ...state,
                ...payload,
            }
            return newstate
        },
    },

    effects: {
        *query({payload}, { call, put }) {
            const { success, data } = yield call(queryTeam, payload)
            if (success) {
                yield put({ type: 'querySuccess', payload: data })
            } else {
                throw data
            }
        }
    }
}