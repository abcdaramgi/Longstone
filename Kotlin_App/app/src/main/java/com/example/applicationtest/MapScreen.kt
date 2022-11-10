package com.example.applicationtest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_map_screen.*
import net.daum.mf.map.api.MapView
import org.jetbrains.annotations.Nullable

class MapScreen : Fragment() {
    private lateinit var mapView: MapView

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

        val mapView = MapView(getActivity())
        val mapViewContainer: ViewGroup = v.findViewById(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)
        return v
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        mapView = view.findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)*/
    }
/*

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }*/
}
