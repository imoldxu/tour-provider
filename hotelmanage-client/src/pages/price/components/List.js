import React, { PureComponent } from 'react'
import PropTypes from 'prop-types'
import { Table, Card } from 'antd'
import { DropOption } from 'components'
import { Trans, withI18n } from '@lingui/react'
import styles from './List.less'

@withI18n()
class List extends PureComponent {

    // generatePriceCard(){
    //     const {dataSource=[]} = this.props

        

    //     // return dataSource.map((item, index)=>{
    //     //     return (<Card title={item.hotel.name}>
    //     //         <Table
                    
    //     //             data={item.pricesList}
    //     //             columns={colume}
    //     //             className={styles.table}
    //     //             //rowKey={index}
    //     //         ></Table>
    //     //     </Card>)
    //     // })
    // }



    render() {

        const { i18n, dataSource } = this.props
        
        const colume = [
            {
                title: '供应商名称',
                dataIndex: 'providerName',
                //key: 'providerName',
                width: '20%',
                render: (text, record) => {
                    return (
                    <span>{record.provider.name}</span>
                    )
                }
            },
            {
                title: '团队价',
                dataIndex: 'teamPrice',
                //key: 'teamPrice',
                width: '10%',
            },
            {
                title: '散客价',
                dataIndex: 'scatteredPrice',
                //key: 'scatteredPrice',
                width: '10%',
            },
            {
                title: '是否含早',
                dataIndex: 'haveBreakfast',
                //key: 'haveBreakfast',
                width: '10%',
                render: (text, record) => {
                    if (record.haveBreakfast){
                        return (
                            <span>是</span>
                        )
                    }else{
                        return (
                            <span>否</span>
                        )
                    }
                }
            },
            {
                title: '加早餐价',
                dataIndex: 'breakfastPrice',
                //key: 'breakfastPrice',
                width: '10%',
            },
            {
                title: '可以加床',
                dataIndex: 'canAddBed',
                //key: 'canAddBed',
                width: '10%',
                render: (text, record) => {
                    if (record.canAddBed){
                        return (
                            <span>可以</span>
                        )
                    }else{
                        return (
                            <span>不能</span>
                        )
                    }
                }
            },
            {
                title: '加床价',
                dataIndex: 'addBedPrice',
                //key: 'addBedPrice',
                width: '10%',
            },
            {
                title: '备注',
                dataIndex: 'remarks',
                //key: 'remarks',
                width: '20%',
            }
        ]

        return (
            <Table
                dataSource={dataSource}
                columns={colume}
                className={styles.table}
                pagination={false}
            ></Table>
        )
    }

}

List.PropTypes = {
    dataSource: PropTypes.array,
}

export default List 