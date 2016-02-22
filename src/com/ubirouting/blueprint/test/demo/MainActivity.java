package com.ubirouting.blueprint.test.demo;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.test.testshitublueprint.R;
import com.ubirouting.blueprint.ShituBlueprintManager;
import com.ubirouting.blueprint.ShituBlueprintManager.OnPositionSuccess;
import com.ubirouting.blueprint.socket.Position;
import com.ubirouting.datalib.entity.Place;
import com.ubirouting.datalib.manager.ShituPlaceDataManager;
import com.ubirouting.datalib.manager.ShituPlaceDataManager.onReturnDataListener;


public class MainActivity extends Activity {

	private ShituBlueprintManager locM;
	private ShituPlaceDataManager dataM;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initShituBlueprintLocation();
    }
    
    @Override
    protected void onDestroy() {
    	stopShituBlueprintLocation();
    	super.onDestroy();
    }
    
    private void initShituBlueprintLocation(){
    	dataM=new ShituPlaceDataManager(this);
        dataM.getData(new onReturnDataListener() {
			
			@Override
			public void onDataReturn(List<Place> arg0) {
				for (Place place : arg0) {
					//���Ի�ȡ����֮ǰ��ӵ���Ϣ
					Log.d("UbiBeaLibTest", "id:"+place.getPlaceId()+",name:"+place.getPlaceName());
				}
				//ֱ��ʹ�ö���
				Place place=arg0.get(0);
				
				//���ֶ�ָ��id
				int id =arg0.get(0).getPlaceId();
				place.setPlaceId(id);
				
			  //locM=new ShituBlueprintManager(MainActivity.this, id, false,new OnPositionSuccess() {
				locM=new ShituBlueprintManager(MainActivity.this, 913, false,new OnPositionSuccess() {
					@Override
					public void onPosition(Position arg0) {
						Log.d("UbiBeaLibTest", arg0.toString());
					}
				});
				
				//������λ
				locM.startBlueprintLocation();
			}
		});
    }
    
	private void stopShituBlueprintLocation() {
		if (locM != null) {
			// ֹͣ��λ
			locM.stopBlueprintLocation();
		}
	}

}
