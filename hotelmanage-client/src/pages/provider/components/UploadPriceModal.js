import React, { Component } from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import { FilterItem } from 'components'

import { Trans } from '@lingui/react'
import { Button, Row, Col, Form, Input, Cascader, Modal } from 'antd'

class UploadPriceModal extends Component{
    formRef = React.createRef()

    onOk = () =>{

        const {handleOk} = this.props;

        this.formRef.current
        .validateFields()
        .then(values => {

            let fileObj = document.getElementById('uploadPrice_file').files[0]
            
            let newValues ={
                providerId: values.providerId,
                file: fileObj,
            }

            if(handleOk){
                handleOk(newValues)
            }
        })
        .catch(info => {
            console.log('校验失败:', info);
        });

        
    }

    render(){

        const {providerId, visible, handleCancel} = this.props;

        return(
            <Modal
                title="上传价格"
                visible={visible}
                onOk={this.onOk}
                confirmLoading={false}
                destroyOnClose={true}
                onCancel={handleCancel}
            >
                <Form
                    ref={this.formRef}
                    name="uploadPrice"
                    initialValues={{providerId:providerId}}
                    preserve={false}
                >
                    <Form.Item name="providerId" noStyle>
                        <Input type='hidden'></Input>
                    </Form.Item>

                    <Form.Item name="file"
                    lable="excel价格文件"
                    rules={[{required:true}]}
                    hasFeedback
                    >
                        <Input type="file"></Input>
                    </Form.Item>
                </Form>
            </Modal>
        )
    }

}

UploadPriceModal.PropTypes={
    handleOk: PropTypes.func,
    handleCancel: PropTypes.func,
    providerId: PropTypes.string,
}

export default UploadPriceModal;