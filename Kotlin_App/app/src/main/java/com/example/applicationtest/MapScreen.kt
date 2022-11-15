package com.example.applicationtest
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.applicationtest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_map_screen.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.jetbrains.annotations.Nullable

class MapScreen : Fragment() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mapView: MapView
    private val ACCESS_FINE_LOCATION = 1000

    companion object {
        fun newInstance(): MapScreen {
            return MapScreen()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
  //      val mapView = MapView(getActivity())

    //    val mapViewContainer = map_view as ViewGroup?
        //val v: View = inflater.inflate(R.layout.fragment_map_screen, container, false)
        //val mapViewContainer: ViewGroup = v.findViewById(R.id.map_view) as ViewGroup
    //    mapViewContainer?.addView(mapView)
    }


    // 프래그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_map_screen, container, false)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        mapView = MapView(getActivity())
        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.798838, 128.583052), true);
        val mapViewContainer: ViewGroup = v.findViewById(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)


//        map_page_location_btn.setOnClickListener {
//            val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
//                val lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//                try {
//                    val userNowLocation: Location? =
//                        lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//                    val uLatitude = userNowLocation.latitude
//                    val uLongitude = userNowLocation.longitude
//                    val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude)
//                    mapView.setMapCenterPoint(uNowPosition, true)
//                }catch(e: NullPointerException){
//                    Log.e("LOCATION_ERROR", e.toString())
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        ActivityCompat.finishAffinity(this)
//                    }else{
//                        ActivityCompat.finishAffinity(this)
//                    }
//
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    System.exit(0)
//                }
//            }else{
//                Toast.makeText(this, "위치 권한이 없습니다.", Toast.LENGTH_SHORT).show()
//                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE )
//            }
//        }
        startTracking()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun inputAddress(){

    }


    private fun startTracking() {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.798838, 128.583052), true);
    }

//
//    override fun onStart() {
//        super.onStart()
//        mapView.onStart()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mapView.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mapView.onPause()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        mapView.onSaveInstanceState(outState)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mapView.onStop()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        mapView.onDestroy()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mapView.onLowMemory()
//    }

}