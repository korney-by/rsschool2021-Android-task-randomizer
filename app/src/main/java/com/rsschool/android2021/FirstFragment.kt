package com.rsschool.android2021


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minEditText: EditText? = null
    private var maxEditText: EditText? = null
    private lateinit var showFragments: ShowFragments

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minEditText = view.findViewById(R.id.min_value)
        maxEditText = view.findViewById(R.id.max_value)


        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = getString(R.string.previous_result, result.toString())


        generateButton?.setOnClickListener {
            val min = Utils.getIntUnsigned(minEditText)
            val max = Utils.getIntUnsigned(maxEditText)
            if (isValueGood(min, max)) {
                showFragments.showSecondFragment(min, max)
            } else {
                showToast(getMessageBadValues(min, max),previousResult?.top as Int-5)
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun showToast(messageBadValues: String, top:Int) {
        val toast :Toast = Toast.makeText(context,messageBadValues,Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP, 0,top)
        //toast.duration=3000
        toast.show()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ShowFragments) {
            showFragments = context //as ShowFragments
        } else {
            throw  RuntimeException(getString(R.string.ex_implements_showfragment, context))
        }

    }

    private fun isValueGood(min: Int, max: Int): Boolean {
        return (min >= 0) && (max > 0) && (min < max) &&
                (minEditText?.text.toString().isNotEmpty()) &&
                (maxEditText?.text.toString().isNotEmpty())
    }

    private fun getMessageBadValues(min: Int, max: Int): String {
        if (min < 0) {
            return getString(R.string.messageValueMoreMAXINT, getString(R.string.min_value), Int.MAX_VALUE)
        }
        if (max < 0) {
            return getString(R.string.messageValueMoreMAXINT, getString(R.string.max_value), Int.MAX_VALUE)
        }

        val minLength = minEditText?.text.toString().length
        val maxLength = maxEditText?.text.toString().length

        if (minLength == 0 && maxLength == 0) {
            return getString(R.string.messageInputMinMax)
        }
        if (minLength == 0) {
            return getString(R.string.messageInputMin)
        }
        if (maxLength == 0) {
            return getString(R.string.messageInputMax)
        }
        if (min >= max) {
            return getString(R.string.messageMaxMoreMin)
        }
        return ""
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}


