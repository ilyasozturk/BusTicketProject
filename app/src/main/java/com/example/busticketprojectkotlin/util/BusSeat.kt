package com.example.busticketprojectkotlin.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.busticketprojectkotlin.R
import com.example.busticketprojectkotlin.model.SelectedSeatModel
import com.example.busticketprojectkotlin.ui.bus_seat_screen.BusSeatViewModel
import com.skydoves.balloon.*

@SuppressLint("ViewConstructor")
class BusSeat(
    context: Context,
    var viewModel: BusSeatViewModel,
    var horizontalScrollView: ViewGroup,
    selectedSeatPrice : String
) : View(context), View.OnClickListener {
    private lateinit var horizontalLinearLayout: LinearLayout
    private lateinit var imageViewLayout: LinearLayout
    private var seatTextViewList = ArrayList<SelectedSeatModel>()
    private var selectedSeatList = ArrayList<SelectedSeatModel>()
    private var totalPrice: Int = 0
    private var seatPrice: Int = selectedSeatPrice.replace("TL","").trim().toInt()
    private lateinit var createSeat: TextView
    private lateinit var balloon: Balloon
    private val showSeatList = ArrayList<TextView>()

    fun makeSeat(seats: String) {
        val seatGaping = 1
        val seat = "/ $seats"

        horizontalLinearLayout = LinearLayout(context)
        imageViewLayout = LinearLayout(context)

        horizontalLinearLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        horizontalLinearLayout.orientation = LinearLayout.HORIZONTAL


        imageViewLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        val busFront = ImageView(context)
        busFront.setImageResource(R.drawable.bus_front_new)
        imageViewLayout.addView(busFront)
        horizontalLinearLayout.addView(imageViewLayout)
        horizontalScrollView.addView(horizontalLinearLayout)

        var seatNo = 0
        var verticalLinearLayout: LinearLayout? = null


        val horizontalAllSeatLayout = LinearLayout(context)
        horizontalAllSeatLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            1F
        )
        horizontalAllSeatLayout.setPadding(
            8 * seatGaping,
            8 * seatGaping,
            8 * seatGaping,
            8 * seatGaping
        )
        horizontalAllSeatLayout.orientation = LinearLayout.HORIZONTAL
        horizontalAllSeatLayout.setBackgroundResource(R.color.bg_bus_color)
        horizontalLinearLayout.addView(horizontalAllSeatLayout)




        for (koltuklar in seat) {
            if (koltuklar == '/') {
                verticalLinearLayout = LinearLayout(context)
                verticalLinearLayout.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,

                    )
                verticalLinearLayout.orientation = LinearLayout.VERTICAL
                horizontalAllSeatLayout.addView(verticalLinearLayout)
            } else if (koltuklar == 'M') {
                seatNo++
                val koltuk = createSeat(seatNo, SeatStatus.STATUS_BOOKED_MAN)
                koltuk.setOnClickListener(this)
                koltuk.tag = SeatStatus.STATUS_BOOKED_MAN
                val seatStatus = SelectedSeatModel(
                    koltuk.text.toString().toInt(),
                    SeatStatus.STATUS_BOOKED_MAN,
                    1,
                    koltuk
                )
                seatTextViewList.add(seatStatus)
                verticalLinearLayout!!.addView(koltuk)
            } else if (koltuklar == 'E') {
                seatNo++
                val koltuk = createSeat(seatNo, SeatStatus.STATUS_AVAILABLE)
                koltuk.setOnClickListener(this)
                koltuk.tag = SeatStatus.STATUS_AVAILABLE
                val seatStatus = SelectedSeatModel(
                    koltuk.text.toString().toInt(),
                    SeatStatus.STATUS_AVAILABLE,
                    0,
                    koltuk
                )
                seatTextViewList.add(seatStatus)
                verticalLinearLayout!!.addView(koltuk)
            } else if (koltuklar == 'W') {
                seatNo++
                val koltuk = createSeat(seatNo, SeatStatus.STATUS_BOOKED_WOMAN)
                koltuk.setOnClickListener(this)
                koltuk.tag = SeatStatus.STATUS_BOOKED_WOMAN
                val seatStatus = SelectedSeatModel(
                    koltuk.text.toString().toInt(),
                    SeatStatus.STATUS_BOOKED_WOMAN,
                    2,
                    koltuk
                )
                seatTextViewList.add(seatStatus)
                verticalLinearLayout!!.addView(koltuk)
            } else if (koltuklar == '_') {
                val koltuk = createSeat(seatNo, SeatStatus.STATUS_ZERO)
                koltuk.text = ""
                verticalLinearLayout!!.addView(koltuk)

            }

        }

    }


    private fun createSeat(seatNo: Int, status: SeatStatus): TextView {
        val seatTextView = TextView(context)
        seatTextView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            0,
            1f
        )
        seatTextView.setPadding(0, 0, 0, 0)
        seatTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        seatTextView.gravity = Gravity.CENTER
        val param = seatTextView.layoutParams as ViewGroup.MarginLayoutParams
        param.setMargins(5, 5, 5, 5)
        seatTextView.layoutParams = param
        seatTextView.text = seatNo.toString()
        seatTextView.id = seatNo

        when (status) {
            SeatStatus.STATUS_BOOKED_WOMAN -> {
                seatTextView.setBackgroundResource(R.drawable.bg_bus_seat_woman)
            }
            SeatStatus.STATUS_BOOKED_MAN -> {
                seatTextView.setBackgroundResource(R.drawable.bg_bus_seat_man)
            }
            SeatStatus.STATUS_AVAILABLE -> {
                seatTextView.setBackgroundResource(R.drawable.bg_bus_seat_empty)
            }
            else -> {
                seatTextView.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        return seatTextView
    }

    override fun onClick(seat: View?) {
        val clickSeat = seat as TextView
        var balloonClickWoman: ImageView? = null
        var balloonClickMan: ImageView? = null


        if (clickSeat.tag == SeatStatus.STATUS_AVAILABLE) {
            if (selectedSeatList.size < 4) {
                balloon = createBalloon(context) {
                    setArrowSize(10)
                    setLayout(R.layout.bus_seat_pop_up)
                    setWidth(BalloonSizeSpec.WRAP)
                    setHeight(BalloonSizeSpec.WRAP)
                    setCornerRadius(6f)
                    setTextIsHtml(true)
                    setBackgroundColorResource(R.color.grey)
                    setLifecycleOwner(lifecycleOwner)
                }

                balloonClickWoman = balloon.getContentView().findViewById(R.id.btnSeatWoman)
                balloonClickMan = balloon.getContentView().findViewById(R.id.btnSeatMan)
                clickSeat.showAlignTop(balloon)
            }

            clickSelectPopUpItem(balloonClickWoman, balloonClickMan, balloon, clickSeat)


        } else if (clickSeat.tag == SeatStatus.STATUS_SELECTED) {
            clickSeat.tag = SeatStatus.STATUS_AVAILABLE
            clickSeat.setBackgroundResource(R.drawable.bg_bus_seat_empty)
            totalPrice -= seatPrice
            viewModel.seatTotalPrice.value = totalPrice

            for (selectedSeat in selectedSeatList) {
                if (clickSeat.id == selectedSeat.seatId) {
                    selectedSeatList.remove(selectedSeat)
                    isSelectedSeat()
                    break
                }
            }
            for(showSeat in showSeatList){
                if(clickSeat.id == showSeat.id){
                    showSeatList.remove(showSeat)
                    viewModel.deleteSeats.value = showSeat
                    isSelectedSeat()
                    break
                }
            }

        }


    }

    fun getSeatList() = seatTextViewList
    fun getSelectedSeatList () = selectedSeatList

    fun getTotalSeatPrice() = totalPrice

    private fun isSelectedSeat() : Boolean{
        viewModel.isSelectedSeat.value = selectedSeatList.isNotEmpty()
        viewModel.selectedSeatList.value = selectedSeatList
        return selectedSeatList.isNotEmpty()
    }



    private fun selectedSeatShow(maleOrFamele: Boolean,seatNo : Int) {

        if (selectedSeatList.isNotEmpty()) {
            val iterator = selectedSeatList.iterator()
            if (iterator.hasNext()) {
                if (maleOrFamele) {
                    createSeat = TextView(context)
                    createSeat.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT

                    )
                    createSeat.setBackgroundResource(R.drawable.bg_bus_seat_woman)
                    createSeat.text = seatNo.toString()
                    createSeat.gravity= Gravity.CENTER
                    createSeat.id =seatNo
                    val param = createSeat.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(5, 2, 5, 2)
                    createSeat.layoutParams = param
                    showSeatList.add(createSeat)
                    viewModel.selectedSeats.value = createSeat
                } else {
                    createSeat = TextView(context)
                    createSeat.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT

                    )
                    createSeat.setBackgroundResource(R.drawable.bg_bus_seat_man)
                    createSeat.gravity= Gravity.CENTER
                    createSeat.text = seatNo.toString()
                    createSeat.id =seatNo
                    val param = createSeat.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(5, 2, 5, 2)
                    createSeat.layoutParams = param
                    showSeatList.add(createSeat)
                    viewModel.selectedSeats.value = createSeat
                }

            }

        }


    }

    private fun clickSelectPopUpItem(
        womanIcon: ImageView?,
        manIcon: ImageView?,
        balloon: Balloon?,
        selectedSeat: TextView
    ) {

        womanIcon?.setOnClickListener {
            selectedSeat.setBackgroundResource(R.drawable.bg_bus_seat_selected)
            val selectedSeatModel =
                SelectedSeatModel(selectedSeat.id, SeatStatus.STATUS_BOOKED_WOMAN, 1, selectedSeat)
            selectedSeatList.add(selectedSeatModel)
            isSelectedSeat()
            totalPrice += seatPrice
            viewModel.seatTotalPrice.value = totalPrice
            selectedSeat.tag = SeatStatus.STATUS_SELECTED
            selectedSeatShow(true,selectedSeat.id)
            balloon?.dismiss()
        }

        manIcon?.setOnClickListener {
            selectedSeat.setBackgroundResource(R.drawable.bg_bus_seat_selected)
            val selectedSeatModel =
                SelectedSeatModel(selectedSeat.id, SeatStatus.STATUS_BOOKED_MAN, 2, selectedSeat)
            totalPrice += seatPrice
            viewModel.seatTotalPrice.value = totalPrice
            selectedSeat.tag = SeatStatus.STATUS_SELECTED
            selectedSeatList.add(selectedSeatModel)
            isSelectedSeat()
            selectedSeatShow(false,selectedSeat.id)
            balloon?.dismiss()
        }
    }


}