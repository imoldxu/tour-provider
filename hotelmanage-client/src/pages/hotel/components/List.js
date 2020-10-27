import React, { PureComponent } from 'react'
import PropTypes from 'prop-types'
import { Table } from 'antd'
import { DropOption } from 'components'
import { Trans, withI18n } from '@lingui/react'
import styles from './List.less'

@withI18n()
class List extends PureComponent {

    handleMenuClick = (record, e) => {
        const { onDeleteItem, onEditItem, i18n } = this.props

        if (e.key === '1') {
            onEditItem(record)
        } else if (e.key === '2') {
            confirm({
                title: i18n.t`Are you sure delete this record?`,
                onOk() {
                console.log("delete")
                onDeleteItem(record.id)
                },
            })
        }
    }

    render() {

        const { onDeleteItem, onEditItem, onShowItem, i18n, ...tableProps } = this.props
        
        const columns = [
            {
                title: '酒店名称',
                dataIndex: 'name',
                key: 'name',
                width: '20%',
                render: (text, record) => {
                    return (
                        <span onClick={() => onShowItem(record)}>{text}</span>
                    )
                }
            },
            {
                title: '城市',
                dataIndex: 'city',
                key: 'city',
                width: '10%',
            },
            {
                title: '地址',
                dataIndex: 'address',
                key: 'address',
                width: '20%',
            },
            {
                title: '区域',
                dataIndex: 'area',
                key: 'area',
                width: '20%',
                render: (text, record) => {
                    let areaSize = record.area.length;
                    let t = "";
                    for(let i=0;i<areaSize;i++){
                        if (i!=0){
                            t = t+","
                        }
                        t = t+record.area[i]
                    }
                    return (<span>{t}</span>)
                },
            },
            {
                title: '携程等级',
                dataIndex: 'xclv',
                key: 'xclv',
                width: '10%',
            },
            {
                title: '标准等级',
                dataIndex: 'lv',
                key: 'lv',
                width: '10%',
            },
            {
                title: '操作',
                key: 'op',
                width: '20%',
                fixed: 'right',
                render: (text, record) => {
                    return (
                        <DropOption
                            onMenuClick={e => this.handleMenuClick(record, e)}
                            menuOptions={[
                                { key: '1', name: i18n.t`Update` },
                                { key: '2', name: i18n.t`Delete` },
                            ]}
                        />
                    )
                },
            },
        ];

        return (
            <Table
                {...tableProps}
                className={styles.table}
                //bordered
                //scroll={{ x: 1200 }}
                columns={columns}
                //simple
                rowKey={record=> record.id}
            ></Table>
        )
    }

}

export default List 